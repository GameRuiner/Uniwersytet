#lang typed/racket

;; definicja typu wyrażeń

(define-type BinopNum (U '+ '- '*))
(define-type BinopRel (U '= '>))
(define-type BinopBool (U 'and 'or))
(define-type BinopSym (U BinopNum BinopRel BinopBool))
(struct expr-binop ([op : BinopSym] [l : Expr] [r : Expr]))
(struct expr-if ([c : Expr] [t : Expr] [f : Expr]))
(struct expr-let ([var : Symbol] [def : Expr] [expr : Expr]))
(define-type Literal (U Integer Boolean))
(define-type Expr (U Symbol Literal expr-binop expr-if expr-let))

(define-predicate literal? Literal)
(define-predicate op-num? BinopNum)
(define-predicate op-rel? BinopRel)
(define-predicate op-bool? BinopBool)

;; środowiska

(define-type Value (U Integer Boolean))
(define-type (Env A) (Listof (List Symbol A)))
(define-type VEnv (Env Value))

(: empty-env (All (A) (-> (Env A))))
(define (empty-env)
  null)

(: add-to-env (All (A) (-> Symbol A (Env A) (Env A))))
(define (add-to-env x v env)
  (cons (list x v) env))

(: find-in-env (All (A) (-> Symbol (Env A) A)))
(define (find-in-env x env)
  (cond [(null? env) (error "undefined variable" x)]
        [(eq? x (caar env)) (cadar env)]
        [else (find-in-env x (cdr env))]))

;; ewaluator

(: op-num->proc (-> BinopNum (-> Value Value Value)))
(define (op-num->proc op)
  (lambda (a b)
    (if (and (number? a) (number? b))
        (cond [(eq? op '+) (+ a b)]
              [(eq? op '*) (* a b)]
              [(eq? op '-) (- a b)])
        (error "type error in op-num->proc"))))

(: op-rel->proc (-> BinopRel (-> Value Value Value)))
(define (op-rel->proc op)
  (lambda (a b)
    (if (and (number? a) (number? b))
        (cond [(eq? op '=) (= a b)]
              [(eq? op '>) (> a b)])
        (error "type error in op-num->proc"))))

(: op-bool->proc (-> BinopBool (-> Value Value Value)))
(define (op-bool->proc op)
  (lambda (a b)
    (if (and (boolean? a) (boolean? b))
        (cond [(eq? op 'and) (and a b)]
              [(eq? op 'or) (or a b)])
        (error "type error in op-num->proc"))))

(: op->proc (-> BinopSym (-> Value Value Value)))
(define (op->proc op)
  (cond [(op-num? op) (op-num->proc op)]
        [(op-rel? op) (op-rel->proc op)]
        [(op-bool? op) (op-bool->proc op)]))

(: eval (-> VEnv Expr Value))
(define (eval env e)
  (cond [(literal? e) e]
        [(symbol? e) (find-in-env e env)]
        [(expr-binop? e)
         ((op->proc (expr-binop-op e))
          (eval env (expr-binop-l e))
          (eval env (expr-binop-r e)))]
        [(expr-if? e)
         (if (eval env (expr-if-c e))
             (eval env (expr-if-t e))
             (eval env (expr-if-f e)))]
        [(expr-let? e)
         (eval (add-to-env (expr-let-var e) (eval env (expr-let-def e)) env)
               (expr-let-expr e))]))

;; typechecker

(define-type EType (U 'integer 'boolean))
(define-type TEnv (Env EType))

(: typeinfer (-> TEnv Expr EType))
(define (typeinfer env e)
  (define (equal-types t1 t2)
    (if (equal? t1 t2) #t (error "type error")))
  (cond [(integer? e) 'integer]
        [(boolean? e) 'boolean]
        [(symbol? e) (find-in-env e env)]
        [(expr-binop? e)
         (define (typeinfer-binop t1 t2)
                (let ([lt (typeinfer env (expr-binop-l e))]
                      [rt (typeinfer env (expr-binop-r e))])
                  (equal-types lt t1)
                  (equal-types rt t1)
                  t2))
         (cond [(op-num? (expr-binop-op e))
                (typeinfer-binop 'integer 'integer)]
               [(op-rel? (expr-binop-op e))
                (typeinfer-binop 'integer 'boolean)]
               [(op-bool? (expr-binop-op e))
                (typeinfer-binop 'boolean 'boolean)])]
        [(expr-if? e)
         (let ([ct (typeinfer env (expr-if-c e))]
               [tt (typeinfer env (expr-if-t e))]
               [ft (typeinfer env (expr-if-f e))])
           (equal-types ct 'boolean)
           (equal-types tt ft)
           tt)]
        [(expr-let? e)
         (let* ([dt (typeinfer env (expr-let-def e))]
                [env1 (add-to-env (expr-let-var e) dt env)]
                [et (typeinfer env1 (expr-let-expr e))])
           et)]))
