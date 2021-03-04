# SO Lista 8(1,2,3)

## Zadanie 1

![](https://i.imgur.com/9xATK7A.png)



brk() ustawia koniec segmentu danych na wartość określoną przez addr.

sbrk() zwiększa przestrzeń danych programu o podaną liczbę bajtów. Wywołanie sbrk() z przyrostem równym 0 może służyć do znalezienia bieżącej lokalizacji przerwy w programie.

Przerwa programu to pierwsza lokalizacja po zakończeniu niezainicjowanego segmentu danych.

mmap() tworzy nowe mapowanie w wirtualnej przestrzeni adresowej procesu wywołującego.

Wywołanie systemowe munmap() usuwa mapowania dla określonego zakresu adresów i powoduje, że dalsze odwołania do adresów w tym zakresie generują nieprawidłowe odwołania do pamięci.

Funkcja malloc() przydziela rozmiar bajtów i zwraca wskaźnik do przydzielonej pamięci.

Funkcja free() zwalnia przestrzeń pamięci wskazywaną przez ptr.

Zarówno brk(), jak i mmap() powodują mapowanie stron do przestrzeni adresowej procesu. mmap() może być używany do mapowania stron pliku w pamięci. brk() to po prostu określony interfejs podsystemu maszyny wirtualnej, który mapuje strony w określonej lokalizacji (na górze sterty). Z drugiej strony mmap() pozwala zmapować dowolną stronę w dowolnym miejscu.

## Zadanie 2

**Fragmentacja wewnętrzna** pojawia się, gdy wolne bloki pamięci są dostępne do alokacji, ale nie mogą być używane do przechowywania obiektów o rozmiarach faktycznie żądanych przez program

**Fragmentacja zewnętrzna** pojawia się, gdy przydzielony jest wystarczająco duży wolny blok do przechowywania obiektu, ale blok jest większy niż potrzeba

**Kompaktowanie** to proces, w którym wolne miejsce jest gromadzone w dużym bloku pamięci.

* Fragmentacja zewnętrzna pojawia się kiedy, obiekty są umieszczane na sąsiednich obszarach i kiedy te obiekty umierają.
* allokator nie ma kontroli nad kolejnością żądań dotyczących fragmentów pamięci o różnej wielkości lub kiedy obiekty są zwalniane
* mogą istnieć wolne obszary, które są zbyt małe, aby obsłużyć żądanie i których sąsiedzi nie są wolni

## Zadanie 3

* Rampy. Wiele programów monotonicznie gromadzi pewne struktury danych w czasie, może to być spowodowane tym, że prowadzą dziennik zdarzeń lub strategia rozwiązywania problemów wymaga zbudowania dużej reprezentacji, po której można szybko znaleźć rozwiązanie.
* Szczyty. Wiele programów używa pamięci we wzorach ciągłych, tworząc stosunkowo duże struktury danych, które są używane przez czas trwania określonej fazy, a następnie odrzucają większość lub wszystkie te struktury danych. Należy zauważyć, że struktury danych, które przetrwały, mogą być różnego typu, ponieważ reprezentują wyniki fazy w przeciwieństwie do wartości pośrednich, które mogą być reprezentowane w inny sposób. Szczyt jest jak rampa, ale ma krótszy czas trwania
* Płaskowyże. Wiele programów szybko tworzy struktury danych, a następnie używa tych struktur danych przez długi czas, często prawie przez cały czas działania programu


![](https://i.imgur.com/2PtWqJo.png)



Obiekty różnego typu mogą służyć różnym celom i umierać w różnym czasie. Rozmiar prawdopodobnie będzie powiązany z rodzajem i przeznaczeniem, więc unikanie mieszania się różnych rozmiarów i prawdopodobnych typów obiektów może zmniejszyć rozpraszanie długowiecznych obiektów wśród tych krótko żyjących.
Sugeruje to, że obiekty przydzielone mniej więcej w tym samym czasie powinny być alokowane obok siebie w pamięci z możliwą poprawką, że obiekty o różnych rozmiarach powinny być segregowane.

* **Best-fit** alokator przeszukuje listę w celu znalezienia najmniejszego wolnego bloku dostatecznie dużego, aby spełnić żądanie. Podstawową strategią jest tutaj zminimalizowanie ilości zmarnowanego miejsca poprzez upewnienie się, że fragmenty są tak małe, jak to tylko możliwe. Ta strategia może się cofnąć w praktyce jeśli są zbyt dobre, ale nie doskonałe w tym przypadku większość każdego bloku zostanie wykorzystana, a pozostała część będzie dość mała i być może bezużyteczna W ogólnym przypadku najlepsze przeszukiwanie jest wyczerpujące, chociaż może się zatrzymać, gdy zostanie znalezione idealne.Wyczerpujące wyszukiwanie oznacza, że sekwencyjne wyszukiwanie nie jest dobrze skalowane do dużych stert z wieloma wolnymi blokami.
* **First-fit** po prostu przeszukuje listę od początku i wykorzystuje pierwszy wolny blok wystarczająco duży, aby spełnić żądanie. Jeśli blok jest większy, wtedy jest dzielony, a pozostała część umieszczana jest na liście wolnych. Problem z sekwencyjnym pierwszym dopasowaniem polega na tym, że większe bloki w pobliżu początku listy są zwykle dzielone jako pierwsze, to powoduje, że wiele małych bloków znajduje się w pobliżu początku listy. To może wydłużyć czas przeszukiwania, ponieważ gromadzi się wiele małych wolnych bloków i wyszukiwanie musi przejść przez nie za każdym razem, gdy żądany jest większy blok. Klasyczne liniowe pierwsze dopasowanie może zatem słabo skalować się do systemów, w których jest przydzielonych wiele obiektów i gromadzi się wiele różnych rozmiarów wolnych bloków.
* **Next-fit** optymalizacją first-fit z użyciem wskaźnika wędrującego do alokacji. Wskaźnik rejestruje pozycję, w której ostatnie wyszukiwanie zostało zakończone, a następne wyszukiwanie rozpocznie się od tego miejsca. Kolejne wyszukiwania cyklicznie przechodzą przez listę. Zwykłym uzasadnieniem jest skrócenie średniego czasu wyszukiwania w przypadku korzystania z listy liniowej, ale ta technika implementacji ma duży wpływ na politykę i skuteczną strategię ponownego wykorzystania pamięci. Ponieważ wskaźnik wędrujący cyklicznie przechodzi przez pamięć, obiekty z różnych faz wykonywania programu mogą zostać wplecione w pamięć. Może to wpłynąć na fragmentację, jeśli obiekty z różnych faz mają różne oczekiwane okresy życia.

## Zadanie 4

**Gorliwie złączenie** w tym momencie jak zwalniamy blok pamięci, to od razu patrzymy czy możemy scalić.


![](https://i.imgur.com/wuckh7S.png)

0____ _____ _____ _____ _____ _____ _____ _____ _____ ____0

alloc(5) 

08*** **/8_ _____ _____ _____ _____ _____ _____ _____ ____0

alloc(12)
08*** **/8|14| ***** *****  **|14|__ _____ _____ _____ _____ ____0

alloc(15) 

08*** **/8|14| ***** *****  **|14||18|* ***** ***** *****/ |18|____ ____0

alloc(8) 
free(@2) 
free(@1) 
free(@3) 
alloc(10)