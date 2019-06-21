#lang racket
(define (dist x y)
  (abs (- x y)))
(define (sqrt x)
  (define (improve approx)
    (/(+ (/ x (expt approx 2)) (* 2 approx))3))
  (define (good-enough? approx)
    (< (dist x (expt approx 3)) 0.0001))
  (define (iter approx)
    (cond
      [(good-enough? approx) approx]
      [else                  (iter (improve approx))]))
  (iter 1.0))

(sqrt 27)
(sqrt 0)
(sqrt 1)
(sqrt 8)
(sqrt 1000)
(sqrt -8)
