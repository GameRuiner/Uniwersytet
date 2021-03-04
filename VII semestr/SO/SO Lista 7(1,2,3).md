# SO Lista 7(1,2,3)

## Zadanie 1

**Stronicowanie na żądanie** W systemie używającym stronicowania na żądanie system operacyjny kopiuje stronę dysku do pamięci fizycznej tylko wtedy, gdy zostanie podjęta próba uzyskania do niej dostępu, a ta strona nie znajduje się w pamięci (tj. jeśli wystąpi błąd strony). Wynika z tego, że proces rozpoczyna wykonywanie, gdy żadna z jego stron nie znajduje się w pamięci fizycznej, i wiele błędów stron będzie występować, dopóki większość roboczego zestawu stron procesu nie zostanie zlokalizowana w pamięci fizycznej. To jest przykład techniki leniwego ładowania.


Zapisywanie do nowego regionu wymaga, aby jądro wykonało pełną alokację pamięci.

## Zadanie 2

**Odwzorowanie plików w pamięć** (ang. memory-mapped files). Odwzorowanie plików odwzorowuje region pliku bezpośrednio do pamięci wirtualnej procesu wywołującego. Po odwzorowaniu pliku można uzyskać dostęp do jego zawartości poprzez operacje na bajtach w odpowiednim regionie pamięci. W razie potrzeby strony odwzorowane są ładowane (automatycznie) z pliku. Ten typ odwzorowania jest również znany jako odwzorowania oparte na plikach lub plik odwzorowany w pamięci.

**Odwzorowanie pamięci anonimowej** (ang. anonymous mappings). Nie ma odpowiedniego pliku. Zamiast tego strony odwzorowania są inicjalizowane na 0.

**Odwzorowanie prywatne**. Modyfikacje zawartości odwzorowania nie są widoczne dla innych procesów i, w przypadku odwzorowania plików, nie są przenoszone do pliku źródłowego. Chociaż strony prywatnego mapowania są początkowo udostępniane w okolicznościach opisanych powyżej, zmiany w treści mapowania są jednak prywatne dla każdego procesu. Jądro dokonuje tego za pomocą techniki kopiowania przy zapisie. Oznacza to, że za każdym razem, gdy proces próbuje zmodyfikować zawartość strony, jądro najpierw tworzy nową, oddzielną kopię tej strony dla procesu (i dostosowuje tabele stron procesu).

**Odwzorowanie dzielone**. Modyfikacje zawartości odwzorowania są widoczne dla innych procesów, które współużytkują to samo odwzorowanie, a w przypadku odwzorowania plików są przenoszone do pliku źródłowego.

## Zadanie 3

**Odwzorowanie prywatne pliku**. Zawartość odwzorowania jest inicjowana z regionu pliku. Wiele procesów odwzorowujących ten sam plik początkowo współdzieli te same fizyczne strony pamięci, ale stosowana jest technika kopiowania przy zapisie, dzięki czemu zmiany odwzorowania dokonane przez jeden proces są niewidoczne dla innych procesów. Głównym zastosowaniem tego typu mapowania jest zainicjowanie obszaru pamięci na podstawie zawartości pliku. Niektóre typowe przykłady to inicjowanie tekstu procesu i zainicjowanych segmentów danych z odpowiednich części binarnego pliku wykonywalnego lub pliku biblioteki współdzielonej.

**Odwzorowanie prywatne pamięci anonimowej**. Proces potomny dziedziczy odwzorowanie swojego rodzica, semantyka kopiowania przy zapisie zapewnia, że po fork () rodzic i dziecko nie widzą zmian dokonanych w odwzorowaniu przez inny proces. Głównym celem prywatnych anonimowych odwzorowań jest przydzielenie nowej (zapełnionej zerami) pamięci dla procesu (np. Malloc () wykorzystuje do tego celu mmap () podczas przydzielania dużych bloków pamięci).

**Odwzorowanie dzielone pliku**. Ten rodzaj odwzorowania służy dwóm celom. Po pierwsze, zezwala na operacje we/wy mapowane w pamięci. Rozumiemy przez to, że plik jest ładowany do regionu pamięci wirtualnej procesu, a modyfikacje w tej pamięci są automatycznie zapisywane w pliku. W związku z tym operacje we/wy odwzorowane w pamięci stanowią alternatywę dla operacji we/wy z użyciem funkcji read() i write(). Drugim celem tego typu mapowania jest umożliwienie niepowiązanym procesom współdzielenia regionu pamięci w celu wykonania (szybkiego) IPC.

**Odwzorowanie dzielone pamięci anonimowej** umożliwia IPC, ale tylko między powiązanymi procesami.

Odwzorowanie jest tracone, gdy proces wykonuje exec(), ale jest dziedziczone przez element potomny fork(). Dziedziczony jest również typ odwzorowania (MAP_PRIVATE lub MAP_SHARED).

Podstawową funkcją **pamięci wymiany** jest zastąpienie miejsca na dysku pamięcią RAM, gdy prawdziwa pamięć RAM się zapełnia i potrzeba więcej miejsca.
