#lang racket

(define/contract (dist x y)
  (-> number? number? number?)
  (abs (- x y)))

(define/contract (square x)
  (-> number? number?)
  (* x x))

(define/contract (inc i)
  (-> integer? integer?)
  (+ i 1))

;; definicje złożonych kontraktów
(define natural/c (and/c integer? (not/c negative?)))
(define exact-natural/c (and/c natural/c exact?))
(define positive-natural/c (and/c integer? positive?))

;; silnia jako procedura rekurencyjna
;; jeśli damy samo natural/c, (fact 1000.0) łamie kontrakt
(define/contract (fact n)
  (-> exact-natural/c positive-natural/c)
  (if (= n 0)
      1
      (* n (fact (- n 1)))))

;; równoważność dwóch definicji przez indukcję, wymaga uogólnienia twierdzenia do
;; dla każdego n, jeśli (natural? n) to dla każdego i (= (fact (+ n i)) (fact-iter (+ n i) i (fact i)))

(define/contract (close-enough? x y)
  (-> number? number? boolean?)
  (< (dist x y) 0.00001))

;; obliczanie (przybliżonego) punktu stałego funkcji f przez iterację, let pozwala uniknąć powtarzania obliczeń
;; test kontraktu: (fix-point positive? 0)
(define/contract (fix-point f x0)
  (-> (-> number? number?) number? number?)
  (let ((x1 (f x0)))
    (if (close-enough? x0 x1)
        x0
        (fix-point f x1))))

;; próba obliczania pierwiastka kwadratowego z x jako punktu stałego funkcji y ↦ x / y zapętla się
;; stosujemy tłumienie z uśrednieniem: procedurę wyższego rzędu zwracającą procedurę jako wynik
(define/contract (average-damp f)
  (-> (-> number? number?) (-> number? number?))
  (lambda (x) (/ (+ x (f x)) 2)))

(define/contract (sqrt-ad x)
  (-> positive? number?)
  (fix-point (average-damp (lambda (y) (/ x y))) 1.0))

;; obliczanie pochodnej funkcji z definicji przyjmując dx za "odpowiednio małą" wartość (zamiast "prawdziwej" granicy)
(define/contract (deriv f)
  (-> (-> number? number?) (-> number? number?))
  (let ((dx 0.000001))
    (lambda (x) (/ (- (f (+ x dx)) (f x)) dx))))

;; przekształcenie Newtona: x ↦ x - f(x) / f'(x) pozwala obliczyć miejsce zerowe f jako punkt stały tej transformacji
(define/contract (newton-transform f)
  (-> (-> number? number?) (-> number? number?))
  (lambda (x)
    (- x
       (/ (f x)
          ((deriv f) x)))))

(define/contract (newtons-method f x)
  (-> (-> number? number?) number? number?)
  (fix-point (newton-transform f) x))

;; zastosowania
(define/contract pi positive? (newtons-method sin 3))

(define/contract (sqrt-nm x)
  (-> positive? number?)
  (newtons-method (lambda (y) (- x (square y))) 1.0))

