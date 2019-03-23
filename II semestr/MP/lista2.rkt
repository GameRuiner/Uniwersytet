#lang racket
(define (square x) (* x x))
(define square1 (lambda (x) (* x x)))
(define (inc x) (+ x 1))
;z2
(define compose (lambda (f g )
                  (lambda (x) (f (g x) )
                  )))
(define (identity x) x)
;z3


(define (repeated p n)
  ( cond ([= n 0] (lambda (x) (identity x)))
         ([= n 1] (lambda (x) (p x)))
         (else (repeated (compose p p) (- n 1)))))


;z4
(define product
  (lambda (term next s e)
    (if (> s e)
        1
        (* (term s) (product term next (next s) e)))))

(define Iproduct
  (lambda (term next s e)
    (define (iter res s)(
    (if (<= s e)
        (term s)
        (iter(* res (term s)) (next s)))))
    (iter 1 s)
        ))
  
(define (pare x) (* (/ (- x 1) x) (/ (+ x 1) x)))