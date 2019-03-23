#lang racket

(require racklog)

(define %my-append
  (%rel (x xs ys zs)
        [(null ys ys)]
        [((cons x xs) ys (cons x zs))
         (%my-append xs ys zs)]))

(define %my-member
  (%rel (x xs y)
        [(x (cons x xs))]
        [(y (cons x xs))
         (%my-member y xs)]))

(define %double
  (%rel (xs ys)
        [(xs ys)
         (%my-append xs xs ys)]))

(define %which-to-del
  (%rel (x xs ys)
        [(xs ys x)
         (%select x xs ys)
         ]))

(define %list-to-append
  (%rel (xs ys zs)
        [(xs ys zs)
         (%my-append xs zs ys)]))

(define %select
  (%rel (x xs y ys)
        [(x (cons x xs) xs)]
        [(y (cons x xs) (cons x ys))
         (%select y xs ys)]))

(define %sublist
  (%rel (xs ys y x)
        [(null xs )]
        [((cons x xs) (cons y ys))
         (%or (%and [%= x y]
                    [%sublist xs ys])
              (%sublist (cons x xs) ys))]))

(define %perm
  (%rel (x zs xs ys)
        [(null null)]
        [((cons x xs) ys)
         (%select x ys zs)
         (%perm xs zs)]))

(define (list->num xs)
  (if (null? xs)
      0
      (+ (* (expt 10 (- (length xs) 1)) (car xs)) (list->num (cdr xs))))) 

;; prosta rekurencyjna definicja
(define %simple-length
  (%rel (x xs n m)
        [(null 0)]
        [((cons x xs) n)
         (%simple-length xs m)
         (%is n (+ m 1))]))

(%let (xs)
      (%which (d e m n o r s y a b)
              (%sublist xs '(0 1 2 3 4 5 6 7 8 9))
              (%simple-length xs 8)
              (%perm (list d m n o r s y) xs)
              (%=/= s 0)
              (%=/= m 0)
              (%is a (+ (list->num (list s e n d))
                        (list->num (list m o r e))))
              (%is b (list->num (list m o n e y)))
              (%= a b)))
;; test w trybie +- (działa)
;(%find-all (a) (%simple-length (list 1 2) a))
;; test w trybie ++ (działa)
;(%find-all () (%simple-length (list 1 2) 2))
;; test w trybie -+ (1 odpowiedź, pętli się)
;(%which (xs) (%simple-length xs 2))
;; test w trybie -- (nieskończona liczba odpowiedzi)
;(%which (xs a) (%simple-length xs a))

;; definicja zakładająca, że długość jest znana
(define %gen-length
  (%rel (x xs n m)
        [(null 0) !]
        [((cons x xs) n)
         (%is m (- n 1))
         (%gen-length xs m)]))
;; test w trybie ++ (działa)
;(%find-all () (%gen-length (list 1 2) 2))
;; test w trybie -+ (działa)
;(%find-all (xs) (%gen-length xs 2))
;(%find-all (x) (%my-append (list 1 2) (list 3 4) x))
;(%find-all (x) (%my-append x ))
;ćw 3
;(%find-all (ys) (%double (list 1 2) ys ))
;(%find-all (x) (%which-to-del (list 1 2 3 4) (list 1 2 4) x))
;(%find-all (zs) (%list-to-append (list 1 2 3) (list 1 2 3 4 5) zs))

;ćw 6
;(%find-all () (%sublist (list 2 1) (list 1 2 3) ))
;(%find-all () (%sublist (list 3 4 5) (list 1 2 3 4 5 6) ))
;(%find-all () (%sublist (list 1 6) (list 1 2 3 4 5 6)))
;(%find-all () (%sublist (list 2 3 4 1) (list 1 2 3 4 5 6)))
;(%find-all () (%sublist (list 1 1 1 1) (list 1 1 1 2 1 6)))
;(%find-all () (%sublist (list 1 1 1 2 2 1) (list 1 1 1  2 2 1 3) ))
;(%find-all (x) (%sublist x (list 1 2 3)))
;ćw 7
;(%find-all (x) (%perm x (list 1 2 3)))
