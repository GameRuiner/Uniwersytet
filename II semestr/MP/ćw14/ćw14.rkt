#lang racket



(define (make-cycle xs)
(define (last-pair xs)
  (if (null? (mcdr xs))
      xs
      (last-pair (mcdr xs))))
  (set-mcdr! (last-pair xs) xs)
  xs)


(define a (make-cycle (mcons 1 (mcons 2 (mcons 3 null)))))


 (define (has-cycle? xs)
   (define (F p1 p2)
     (cond [(or (null? p2) (null? (mcdr p2))) #f]
           [(eq? p1 p2) #t]
           [else (F (mcdr p1)(mcdr (mcdr p2)))]))
   (F xs (mcdr xs)))