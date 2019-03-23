#lang racket

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

(define (op->proc op)
  (cond [(eq? op '+) +]
        [(eq? op '*) *]
        [(eq? op '-) -]
        [(eq? op '/) /]))

(define (let-def? t)
  (and (list? t)
       (= (length t) 2)
       (symbol? (car t))))

(define (let-def-var e)
  (car e))

(define (let-def-expr e)
  (cadr e))

(define (let-def-cons x e)
  (list x e))

(define (let? t)
  (and (list? t)
       (= (length t) 3)
       (eq? (car t) 'let)
       (let-def? (cadr t))))

(define (let-def e)
  (cadr e))

(define (let-expr e)
  (caddr e))

(define (let-cons def e)
  (list 'let def e))

(define (var? t)
  (symbol? t))

(define (var-var e)
  e)

(define (var-cons x)
  x)

(define (hole? t)
  (eq? t 'hole))

(define (arith/let/holes? t)
  (or (hole? t)
      (const? t)
      (and (binop? t)
           (arith/let/holes? (binop-left  t))
           (arith/let/holes? (binop-right t)))
      (and (let? t)
           (arith/let/holes? (let-expr t))
           (arith/let/holes? (let-def-expr (let-def t))))
      (var? t)))

(define (num-of-holes t)
  (cond [(hole? t) 1]
        [(const? t) 0]
        [(binop? t)
         (+ (num-of-holes (binop-left  t))
            (num-of-holes (binop-right t)))]
        [(let? t)
         (+ (num-of-holes (let-expr t))
            (num-of-holes (let-def-expr (let-def t))))]
        [(var? t) 0]))

(define (arith/let/hole-expr? t)
  (and (arith/let/holes? t)
      (= (num-of-holes t) 1)))

(define (hole-context e)
  (define (aux e v)
    (cond [(hole? e) v]
          [(binop? e) (or (aux (binop-left e) v)
                          (aux (binop-right e) v))]
          [(const? e) false]
          [(var? e) false]
          [(let? e)   (let ((nv (if (member (let-def-var (let-def e)) v)
                         v
                         (append v (cons (let-def-var (let-def e)) null)))))
                      (or (aux (let-def-var (let-def e)) v)
                          (aux (let-def-expr (let-def e)) v)
                          (aux (let-expr e) (if (var? nv)
                                                 (cons nv null)
                                                 nv))))]))
  (aux e '()))

(define (test)
  (define tests (list (cons '(+ 3 hole ) '())
                      (cons '( let ( x 3) ( let ( y 7) (+ x hole ) ) ) '(x y))
                      (cons '( let ( x 3) ( let ( y hole ) (+ x 3) )) '(x))
                      (cons '( let ( x hole ) ( let ( y 7) (+ x 3) )) '())
                      (cons '( let ( piesek 1)
                             ( let ( kotek 7)
                             ( let ( piesek 9)
                             ( let ( chomik 5)
                             hole)))) '(piesek kotek chomik))
                      (cons '(+ ( let ( x 4) 5) hole ) '())))
  (define (aux t)
    (cond [(null? t) true]
          [(not(eq-list? (hole-context (car (car t))) (cdr (first t)))) (print(hole-context (car (car t)))) (print (cdr (first t))) false]
          [else (aux (cdr t))]))
  (aux tests))

(define (eq-list? xs ys)
  (cond [ (null? xs) (if (null? ys)
                         true
                         false)]
        [ (not (= (length xs) (length ys))) false]
        [ (member (car xs) ys) (eq-list? (cdr xs) (without-p (car xs) ys))]
        [ else false]))

(define (without-p p xs)
  (if (eq? (car xs) p)
      (cdr xs)
      (cons (car xs) (without-p p (cdr xs)))))

(test)
