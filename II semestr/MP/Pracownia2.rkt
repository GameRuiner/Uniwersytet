#lang racket
(define (square x) (* x x))
(define (inc x) (+ x 1))
(define (compose f g) (lambda (x y z) (f x y (g x y z))))
(define (identity x y z) x y z)
; zadanie3
(define (repeated p n)
  (if (= n  0)
      identity
      (compose p (repeated p  (- n 1)))))
;zadanie4
(define product
  (lambda (term next s e)
    (if (> s e)
        1
        (* (term s) (product term next (next s) e)))))

(define iproduct
  (lambda ( res term next s e)
    (if (> s e)
        res
        (iproduct  (* res (term s)) term next (next s) e))))

(define (term x)
  (* (/ (- x 1) x) (/ (+ x 1) x)))

(define (next x)
  (+ x 2))

;zadanie5
(define (accumulate combiner null-value term a next b)
  (if (> a b)
      null-value
      (combiner (term a) (accumulate  combiner null-value term (next a) next b))))

(define (iaccumulate combiner null-value term a next b)
  (define (iter res a)
    (if (> a b)
        res
        (iter (combiner res (term a)) (next a))))
  (iter null-value a))

;zadanie6
(define (cont-frac num den k)
  (define (iter s)
    (if (> s k)
        (/ (num s) (den s))
        (/ (num s) (+ (den s) (iter (+ s 1))))))
  (iter 1))

(define (ICont-frac num den k)
  (define (iter res s)
    (if (> s 0)
        (iter (/ (num s) (+ (den s) res)) (- s 1))
        res))
  (iter 0 k))
;(cont-frac (lambda (i) 1.0) (lambda (i) 1.0) 10)
;(ICont-frac (lambda (i) 1.0) (lambda (i) 1.0) 10)

;zadanie7
(define (num7 i)
  (square(- (* 2.0 i) 1)))
(define (dem7 i)
  6)
;(+ 3 (cont-frac num7 dem7 14))

;zadanie8

(define (dem8 i)
  (- (* i 2) 1))
(define (atan-cf x k)
  (define (num8 i )
    (if (= i 1)
       x
      (square  (* (- i 1) x) )))
  (cont-frac num8 dem8 k ))
    
;(atan-cf 1.0 10)
;(atan 1)

;zadanie9

(define (build n d b)
  (/ n (+ d b)))

(define (repeated-build k n d b)
  ((repeated build k) n d b))

;(repeated-build 2 1 2 3)
;(build 1 2 (build 1 2 3))

;zadanie10
(define x 1.0)
(define (num8 i )
    (if (= i 1)
       x
      (square  (* (- i 1) x) )))


(define (approx num8 dem8)
  (define (An resA oldresA n)
    (+ (* (dem8 n) resA) (* (num8 n) oldresA)))
  (define (Bn resB oldresB n)
    (+ (* (dem8 n) resB) (* (num8 n) oldresB)))
  (define (fn An Bn)
    (/ An Bn)) 
  (define (goodenough? resA resB oldresA oldresB n)
    (if (< (abs (- (fn resA resB) (fn oldresA oldresB))) 0.001)
        (cont-frac num8 dem8 n)
        (let ((tresa resA)
               (tresb resB)
               (resA (An resA oldresA n))
               (resB (Bn resB oldresB n)))
         (goodenough? resA resB tresa tresb (+ n 1)))))
  (define A1 (An 0 1 1))
  (define A2 (An A1 0 2))
  (define B1 (Bn 1 0 1))
  (define B2 (Bn B1 1 2))
  (goodenough? A2 B2 A1 B1 3))

(approx num8 dem8)
(atan 1)       
        
