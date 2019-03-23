#lang racket
;czw1 
(define (make-rat x y)
  (cons x (cons y null)))

(define (rat-num x)
  (car x))

(define (rat-dem x)
  (car (cdr x)))

(define (rat? x)
  (and (pair? x)
       (pair? (cdr x))))

;czw2
( define ( display-point p )
( display "(")
( display ( point-x p ) )
( display ", ")
( display ( point-y p ) )
( display ")") )

( define ( display-vect v )
( display "[")
( display-point ( vect-begin v ) )
( display ", ")
( display-point ( vect-end v ) )
( display "]") )

(define (make-vect x1 y1 x2 y2)
  (cons (cons x1 y1) (cons x2 y2)))

(define (vect? v)
  (and (pair? v)
       (pair? (car v))
       (pair? (cdr v))))

(define (vect-begin v)
  (car v))

(define (vect-end v)
  (cdr v))

(define (make-point x y)
  (cons x y))

(define (point-x p)
  (car p))

(define (point-y p)
  (cdr p))

(define (vect-length v)
  (let ((x (- (point-x (cdr v)) (point-x (car v))))
        (y (- (point-y (cdr v)) (point-y (car v)))))
    (sqrt (+ (* x x) (* y y)))))

(define (vect-scale v k)
  (let ((x1 (* k (point-x (car v))))
        (x2 (* k (point-x (cdr v))))
        (y1 (* k (point-y (car v))))
        (y2 (* k (point-y (cdr v)))))
    (make-vect x1 y1 x2 y2)))

(define (vect-translate v p)
  (let ((x1 (point-x p))
        (y1 (point-y p))
        (ox1 (point-x (car v)))
        (oy1 (point-y (car v)))
        (ox2 (point-x (cdr v)))
        (oy2 (point-y (cdr v))))
    (let(
        (x2 (+ ox2 (- x1 ox1)))
        (y2 (+ oy2 (- y1 oy1))))
    (make-vect x1 y1 x2 y2))))
