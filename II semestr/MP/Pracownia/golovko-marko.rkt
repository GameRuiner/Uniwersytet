#lang racket
(define (dist x y)
  (abs (- x y)))
(define (identity x ) x )
(define (compose f g) (lambda (x) (f (g x))))
(define (repeated p n)
  (if (= n  0)
      identity
      (compose p (repeated p  (- n 1)))))

(define (close-enough? x y)
  (< (dist x y) 0.00001))

(define (fix-point f x0)
  (let ((x1 (f x0)))
    (if (close-enough? x0 x1)
        x0
        (fix-point f x1))))

(define (average-damp f)
  (lambda (x) (/ (+ x (f x)) 2)))

(define (nth-root x n)
  (fix-point ((repeated average-damp  (- n 1)) (lambda (y) (/ x (expt y (- n 1)))))   1.0) )

(nth-root 16 4)
(nth-root 32 5)
(nth-root 64 6)
(nth-root 8 3)
(nth-root 4 2)
(nth-root 2 1)
