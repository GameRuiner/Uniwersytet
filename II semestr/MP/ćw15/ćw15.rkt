#lang racket
;ćw 1
(define balance 100)
;a)
(set! balance (+ balance 10))
(set! balance (- balance 20))
(set! balance (- balance (/ balance 2)))
;balance {35,40,45,50}
;b) tylko pierwsze dwa
;balance 90 i {110,80}

;ćw 2

(define (run-concurrent . thunks)
  (define threads (map thread thunks))
  (for-each thread-wait threads))

(define x 10)
(run-concurrent
 (lambda () (set! x (* x x )))
 (lambda () (set! x (* x x x))))

;wartości {10^2, 10^6}


;ćw 3

(define (random-sleep)
  (sleep (/ (random) 100)))

(define (with-random-sleep proc)
  (lambda args
    (random-sleep)
    (apply proc args)))


(define (make-serializer)
  (define sem (make-semaphore 1))
  (lambda (proc)
    (lambda args
      (semaphore-wait sem)
      (define ret (apply proc args))
      (semaphore-post sem)
      ret)))

(define (make-account-serialized balance)
  (define (withdraw amount)
    (random-sleep)
    (if (>= balance amount)
        (begin (set! balance ((with-random-sleep -) balance amount))
               balance)
        "Insufficient funds"))
  (define (deposit amount)
    (random-sleep)
    (set! balance ((with-random-sleep +) balance amount))
    balance)
  (define protected (make-serializer))
  (define (dispatch m)
    (cond [(eq? m 'withdraw) (protected withdraw)]
          [(eq? m 'deposit) (protected deposit)]
          [(eq? m 'balance) balance]
          [else (error "Unknown request -- MAKE-ACCOUNT"
                       m)]))
  dispatch)

(define (make-account-serializedP balance)
  (define (withdraw amount)
    (random-sleep)
    (if (>= balance amount)
        (begin (set! balance ((with-random-sleep -) balance amount))
               balance)
        "Insufficient funds"))
  (define (deposit amount)
    (random-sleep)
    (set! balance ((with-random-sleep +) balance amount))
    balance)
  (define protected (make-serializer))
  (define (dispatch m)
    (cond [(eq? m 'withdraw) (protected withdraw)]
          [(eq? m 'deposit) (protected deposit)]
          [(eq? m 'balance) (protected balance)]
          [else (error "Unknown request -- MAKE-ACCOUNT"
                       m)]))
  dispatch)

;nie ma różnicy


;ćw 4

( define ( transfer from-account to-account amount )
(( from-account 'withdraw ) amount )
(( to-account 'deposit ) amount ))

;ok,zależy od interfejsu

;ćw 5

(define s (make-serializer))
;((s (s (s +)))1 1)
(define f (lambda ()  5))
;((s (s (s f))))
;ten sam serializator blokuje sam siebie

;ćw 6

