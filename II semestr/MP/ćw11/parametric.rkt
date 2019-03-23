#lang racket

;; funkcja identycznościowa -- zwraca argument niezmieniony
;; kontrakt gwarantuje to
(define/contract (id x)
  (let ([a (new-∀/c 'a)])
    (-> a a))
  x)

;; funkcja konkatenacji list
;; kontrakt gwarantuje, że elementami listy wyjściowej będą
;; tylko elementy list wejściowych
(define/contract (append xs ys)
  (let ([a (new-∀/c 'a)])
    (-> (listof a) (listof a) (listof a)))
  (if (null? xs)
      ys
      (cons (car xs) (append (cdr xs) ys))))

;; funkcja map
;; kontrakt gwarantuje, że funkcja f otrzyma jako parametr wyłącznie
;; elementy z listy wejściowej, zaś na liście wyjściowej znajdą się
;; wyłącznie wyniki funkcji f
(define/contract (map f xs)
  (let ([a (new-∀/c 'a)]
        [b (new-∀/c 'b)])
    (-> (-> a b) (listof a) (listof b)))
  (if (null? xs)
      null
      (cons (f (car xs))
            (map f (cdr xs)))))

;; funkcja fold
;; kontrakt gwarantuje prawidłowe użycie funkcji op
(define/contract (fold-right op nval xs)
  (let ([a (new-∀/c 'a)]
        [b (new-∀/c 'b)])
    (-> (-> a b b) b (listof a) b))
  (if (null? xs)
      nval
      (op (car xs)
          (fold-right op nval (cdr xs)))))

(require quickcheck)

;; funkcja map zachowuje długość
(quickcheck
 (property ([l (arbitrary-list arbitrary-symbol)]
            [p (arbitrary-procedure arbitrary-symbol arbitrary-symbol)])
           (eq? (length l) (length (map p l)))))

;; długość konkatenacji list to suma długości list wejściowych
(quickcheck
 (property ([l1 (arbitrary-list arbitrary-symbol)]
            [l2 (arbitrary-list arbitrary-symbol)])
           (eq? (length (append l1 l2)) (+ (length l1) (length l2)))))

;; rozdzielność append względem map
(quickcheck
 (property ([l1 (arbitrary-list arbitrary-symbol)]
            [l2 (arbitrary-list arbitrary-symbol)]
            [p (arbitrary-procedure arbitrary-symbol arbitrary-symbol)])
           (equal? (map p (append l1 l2)) (append (map p l1) (map p l2)))))

;; błędna implementacja map -- odwraca listę
;; podany kontrakt nie sprawdza kolejności
(define/contract (bad-map f xs)
  (let ([a (new-∀/c 'a)]
        [b (new-∀/c 'b)])
    (-> (-> a b) (listof a) (listof b)))
  (define (map-iter xs ys)
    (cond [(null? xs) ys]
          [else (map-iter (cdr xs) (cons (f (car xs)) ys))]))
  (map-iter xs '()))

;; błędna implementacja map nie ma własności rozdzielności względem append
(quickcheck
 (property ([l1 (arbitrary-list arbitrary-symbol)]
            [l2 (arbitrary-list arbitrary-symbol)]
            [p (arbitrary-procedure arbitrary-symbol arbitrary-symbol)])
           (equal? (bad-map p (append l1 l2)) (append (bad-map p l1) (bad-map p l2)))))
