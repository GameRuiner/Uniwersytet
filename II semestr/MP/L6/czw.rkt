
#lang racket

;; arithmetic expressions

(define (const? t)
  (number? t))

(define (binop? t)
  (and (list? t)
       (= (length t) 3)
       (member (car t) '(+ - * /))))

(define (binop-op e)
  (car e))

(define (binop-left e)
  (cadr e))

(define (binop-right e)
  (caddr e))

(define (binop-cons op l r)
  (list op l r))

(define (arith-expr? t)
  (or (const? t)
      (and (binop? t)
           (arith-expr? (binop-left  t))
           (arith-expr? (binop-right t)))))

(define (op->proc op)
  (cond [(eq? op '+) +]
        [(eq? op '*) *]
        [(eq? op '-) -]
        [(eq? op '/) /]))

(define (eval-arith e)
  (cond [(const? e) e]
        [(binop? e)
         ((op->proc (binop-op e))
            (eval-arith (binop-left  e))
            (eval-arith (binop-right e)))]))

(define (append xs ys)
  (if (null? xs)
      ys
      (cons (car xs) (append (cdr xs) ys))))

(define e1 '(+ 3 (- 2 (* 3 3))))
;; 3 3 * 2 - 3 +
;(eval-arith e1)

(define (arith=>rpn e)
  (cond [(const? e) e]
        [(binop? e)
        (flatten (list (arith=>rpn(binop-left e))
               (arith=>rpn(binop-right e))
               (binop-op e)))]))

(define (arith=>rpn3 e)
  (define (iter a n)
    (cond [(const? a) (cons a n)]
          [(binop? a)
           (iter (binop-left a)
           (iter (binop-right a)
           (cons (binop-op a) n)))]))
  (iter e null))


(define (stack? s)
  (list? s))

(define (push e s)
  (cons e s))

(define (pop s)
  (cdr s))


(define (eval-rpn a)
  (define (iter s a)
  (if (null? a)
       (car s)
  (let ((e (car a)))
   (if (const? e)
      (iter (push e s) (cdr a))
      (iter (push ((op->proc e) (car (pop s)) (car s)) (pop (pop s)))  (cdr a))))))
  (iter null a))

(define e2 (arith=>rpn3 e1))
e2
(eval-rpn e2)