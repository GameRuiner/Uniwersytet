#lang racket

;; expressions

(define (const? t)
  (number? t))

(define (op? t)
  (and (list? t)
       (member (car t) '(+ - * /))))

(define (op-op e)
  (car e))

(define (op-args e)
  (cdr e))

(define (op-cons op args)
  (cons op args))

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

(define (arith/let-expr? t)
  (or (const? t)
      (and (op? t)
           (andmap arith/let-expr? (op-args t)))
      (and (let? t)
           (arith/let-expr? (let-expr t))
           (arith/let-expr? (let-def-expr (let-def t))))
      (var? t)))

;; let-lifted expressions

(define (arith-expr? t)
  (or (const? t)
      (and (op? t)
           (andmap arith-expr? (op-args t)))
      (var? t)))

(define (let-lifted-expr? t)
  (or (and (let? t)
           (let-lifted-expr? (let-expr t))
           (arith-expr? (let-def-expr (let-def t))))
      (arith-expr? t)))

;; generating a symbol using a counter

(define (number->symbol i)
  (string->symbol (string-append "x" (number->string i))))

;; environments (could be useful for something)

(define empty-env
  null)

(define (add-to-env x v env)
  (cons (list x v) env))

(define (find-in-env x env)
  (cond [(null? env) (error "undefined variable" x)]
        [(eq? x (caar env)) (cadar env)]
        [else (find-in-env x (cdr env))]))

(define (inc i)
  (+ i 1))

;; the let-lift procedure

(define (let-lift e)
  
  (define (find-arith e) ;;wyrażenia bez let
    (cond [(op? e) (cons (op-op e) (map find-arith (op-args e)))]
          [(const? e) e]
          [(var? e) e]
          [(let? e)
              (find-arith (let-expr e))]))
  
  (define (find-def e) ;; lety 
    (cond [(op? e) (append-map find-def (op-args e)) ]
          [(let? e) 
           (append (find-def (let-def-expr (let-def e)))
                            (list (let-def-var (let-def e)) (find-arith (let-def-expr(let-def e))))
                         (find-def (let-expr e)))]
          [else null]))

  
  (define (make-exp def ar) ;;łączy lety i wyrażenia bez let
    (if (= 2 (length def))
        (let-cons def ar)
        (let-cons (let-def-cons (first def) (second def)) (make-exp (cddr def) ar))))

  (define (change e i) ;;zmiana nazw zmiennych
    
    (define (aux args i) ;;są kilka aux bo nie umiem map na wieloargumentowych procedurach
      (if (null? args)
          args
          (cons (change (car args) i) (aux (cdr args) (inc i)))))

   (define (OneDoesNotSimply e nv ov) ;;zrobic to bez środowisk
     (define (aux args nv ov)
      (if (null? args)
          args
          (cons (OneDoesNotSimply (car args) nv ov) (aux (cdr args) nv ov))))
     
     (cond [(op? e) (op-cons (op-op e) (aux (op-args e) nv ov))]
           [(let? e) (let-cons (let-def-cons
                                  (let-def-var (let-def e))
                              (OneDoesNotSimply (let-def-expr (let-def e)) nv ov)) 
                              (if (eq? ov (let-def-var (let-def e)))
                                  (let-expr e)
                                  (OneDoesNotSimply (let-expr e) nv ov)))]
           [(var? e) (if (eq? ov e)
                         nv
                         e)]
           [(const? e) e]
            ))
    
    (cond [(op? e) (op-cons (op-op e) (aux (op-args e) i))]
          [(let? e) (let ((nv (number->symbol i))
                           (ov (let-def-var (let-def e)))   )
                    (let-cons (let-def-cons
                                  nv;;(let-def-var (let-def e))
                                 (change (let-def-expr (let-def e)) (inc i)))
                              (change (OneDoesNotSimply (let-expr e) nv ov) (inc (inc i)))))]
          [else e]))
  (define ne (change e 1) )
   (make-exp (find-def ne) (find-arith ne))
  )
    
    
 


(define e1 '(+ 10 (* ( let ( x 7) (+ x 2) ) 2) ))
(define e2 '( let ( x (- 2 ( let ( z 3) z ) ) ) (+ x 2) ))
(define e3 '(+ ( let ( x 5) x )
               ( let ( x 1) x ) ))

(define e4 ' (+ ( let ( x 5) (let (y 6) (+ x y) ))
                ( let ( x 3) (let (y 7) (- y x)))
                ))
e1
(let-lift e1)
(let-lifted-expr? (let-lift e1))
e2
(let-lift e2)
(let-lifted-expr? (let-lift e2))
e3
(let-lift e3)
(let-lifted-expr? (let-lift e3))
e4
(let-lift e4)
(let-lifted-expr? (let-lift e4))
  
  