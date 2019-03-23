#lang racket

(define (lcons x f)
  (cons x f))

(define (lhead l)
  (car l))

(define (ltail l)
  ((cdr l)))

(define (ltake n l)
  (if (or (null? l) (= n 0))
      null
      (cons (lhead l)
            (ltake (- n 1) (ltail l)))))

(define (lfilter p l)
  (cond [(null? l) null]
        [(p (lhead l))
         (lcons (lhead l)
                (lambda ()
                  (lfilter p (ltail l))))]
        [else (lfilter p (ltail l))]))

(define (lmap f . ls)
  (if (ormap null? ls) null
      (lcons (apply f (map lhead ls))
             (lambda ()
               (apply lmap (cons f (map ltail ls)))))))

;; ciąg Fibonacciego

(define fib
  (lcons 0
         (lambda ()
           (lcons 1
                  (lambda ()
                    (lmap + fib (ltail fib)))))))
           
;; spamiętywanie

(define (memo-proc proc) 
  (let ((already-run? false) (result false))
    (lambda ()
      (if (not already-run?)
          (begin (set! result (proc))
                 (set! already-run? true)
                 result)
          result))))

;(define (lcons x f)
;  (cons x (memo-proc f)))

;; alternatywna implementacja wykorzystująca listy modyfikowalne

;(define (lcons x f)
;  (mcons x f))

;(define (lhead l)
;  (mcar l))

;(define (ltail l)
;  (when (procedure? (mcdr l))
;      (set-mcdr! l ((mcdr l))))
;  (mcdr l))

;; dodatkowy przykład: liczby pierwsze

(define (integers-starting-from n)
  (lcons n (lambda () (integers-starting-from (+ n 1)))))

(define naturals (integers-starting-from 0))

(define (divisible? x y) (= (remainder x y) 0))
(define no-sevens
  (lfilter (lambda (x) (not (divisible? x 7)))
           naturals))

(define (sieve stream)
  (lcons
   (lhead stream)
   (lambda ()
     (sieve (lfilter
             (lambda (x)
               (not (divisible? x (lhead stream))))
             (ltail stream))))))

(define primes (sieve (integers-starting-from 2)))