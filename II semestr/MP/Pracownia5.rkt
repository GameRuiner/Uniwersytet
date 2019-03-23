#lang racket
( define ( var? t )
(symbol? t ) )

( define ( neg? t )
( and ( list? t )
(= 2 ( length t ) )
( eq? 'neg ( car t ) ) ) )

( define ( conj? t )
( and ( list? t )
(= 3 ( length t ) )
( eq? 'conj ( car t ) ) ) )

( define ( disj? t )
( and ( list? t )
(= 3 ( length t ) )
( eq? 'disj ( car t ) ) ) )

( define ( prop? f )
(or ( var? f )
( and ( neg? f )
( prop? (neg-subf f ) ) )
( and ( disj? f )
( prop? (disj-left f ) )
( prop? (disj-right f ) ) )
( and ( conj? f )
( prop? (conj-left f ) )
( prop? (conj-right f ) ) ) ) )


(define (append xs ys)
  (if (null? xs)
      ys
      (cons (car xs) (append (cdr xs) ys))))

(define (neg f)
  (list 'neg f))

(define (conj fl fr)
  (list 'conj fl fr))

(define (disj fl fr)
  (list 'disj fl fr))

(define (neg-subf f)
  (second f))

(define (disj-left f)
  (second f))

(define (disj-right f)
  (third f))

(define (conj-left f)
  (second f))

(define (conj-right f)
  (third f))

(define (in-xs? var xs)
  (if (null? xs)
      false
      (if (eq? var (car xs))
          true
          (in-xs? var (cdr xs)))))

(define (only-one xs ys)
  (if (var? xs)
      (if (in-xs? xs ys)
          ys
          (append (cons xs null) ys))
  (if (null? xs)
      ys
      
  (if (in-xs? (car xs) ys)
      (only-one (cdr xs) ys)
      (only-one (cdr xs) (append (cons (car xs) null) ys))))))


(define (free-vars f )
   (define (vars f xs)
   (if ( var? f )
       (cons f null)
    (cond
       [ (neg? f ) (append (free-vars (neg-subf f)) xs)]
       [ (conj? f) (append (free-vars (conj-right f)) (append (free-vars (conj-left f)) xs)) ]
       [ (disj? f) (append (free-vars (disj-right f)) (append (free-vars (disj-left f)) xs)) ]
       )))
   (let ((xs (vars f null)))
     (only-one xs null)))

(define xs (free-vars f))
(free-vars f)
(in-xs? 'a xs)

(define (gen-vals xs)
  (if (null? xs)
      (list null)
      (let*
          ((vss (gen-vals (cdr xs)))
           (x (car xs))
           (vst (map (lambda (vs) (cons (list x true) vs)) vss))
           (vsf (map (lambda (vs) (cons (list x false) vs)) vss)))
        (append vst vsf))))


(define (eval-formula formula var)
        (cond
          [(disj? formula) (or  (eval-formula (disj-left formula) var)
                          (eval-formula (disj-right formula) var))]
          [(conj? formula) (and  (eval-formula (conj-left formula) var)
                          (eval-formula (conj-right formula) var))]
          [(neg? formula) (not (eval-formula (neg-subf) var))]
          [(var? formula) (eval-var formula var)]))

(define (eval-var var varring)
  (let ((res (assoc varring var)))
    (if res (cdr res) (error "////"))))

(define (literal neg var)
  (list 'literal neg var))

(define (literal? f)
  (or (var? f)
      (and (neg? f)
           (and (neg? f)
                (var? (neg-subf f))))))

(define (literal-var l)
  (third l))

(define (literal-neg l)
  (second l))

(define (nnf? f)
  (cond [(literal? f) true]
        [(disj? f) (and (nnf? (disj-left f))
                        (nnf? (disj-right f)))]
        [(conj? f) (and (nnf? (conj-left f))
                        (nnf? (conj-right f)))]))


(define (eval-literal l valuing)
  (let ((res (eval-var (literal-var l) valuing)))
        (if (literal-neg l)
            (not res)
            res)))


(define (convert-to-nnf f)
  (cond [(var? f) (literal true f)]
        [(disj? f) (disj
                    (convert-to-nnf
                     (disj-left f))
                    (convert-to-nnf
                     (disj-right f)))]
        [(neg? f) (nnf-negate (neg-subf f))]


        ))

(define (nnf-negate f)
  (cond [(var? f) (literal false f)]
        [(disj? f) (

                    )]))

(define (convet-to-cnf f)
  (cond [(literal? f) (list '(f))]
        [(conj? f)(append
                   (convert-to-cnf (conj-left t f))
                   (convert-to-cnf (conj-right f)))]
        [(disj? f)
         (let ((L1 (convert-to-cnf (disj-left f)))
               (L2 (convert-to-cnf (disj-right f)))
                   
              
  




