# Ćwiczenia 1 (8 października 2020)

###### tags: `so20` `ćwiczenia` `kba`

## Deklaracje

Gotowość rozwiązania zadania należy wyrazić poprzez postawienie X w odpowiedniej kolumnie! Jeśli pożądasz zreferować dane zadanie w trakcie dyskusji (co najwyżej jedno!) oznacz je znakiem ==X== na żółtym tle.

**UWAGA: Tabelkę wolno edytować tylko wtedy, gdy jest na zielonym tle!**

:::danger
|                       | 1-1 | 1-2 | 1-3 | 1-4 | 1-5 | 1-6 | 1-7 |
| ---------------------:| --- | --- | --- | --- | --- | --- | --- |
|         Anadi Agrawal |  X  |  X  |  X  |     |  X  |  X  |  X  |
|         Andriy Bernad |  X  |  X  |  X  |  X  |     |     |     |
|         Marko Golovko |  X  |  X  |  X  |     |  X  |     |     |
|    Ireneusz Gościński |  X  |  X  |  X  |     |  X  |     |  X  |
|        Michał Górniak |  X  |  X  |  X  |     |  X  |     |  X  |
|        Artur Juraszek |     |  X  |     |  X  |     |  X  |     |
|    Krzysztof Juszczyk |  X  |  X  |  X  |  X  |  X  |  X  |  X  |
|      Heorhii Karpenko |   X |  X  |  X  |  X  |     |     |     |
|         Wiktoria Kuna |  X  |  X  |  X  |  X  |     |  X  |  X  |
|     Michał Myczkowski |  X  |  X  |  X  |     |  X  |     |     |
|         Hubert Obrzut |  X  |  X  |  X  |  X  |  X  |  X  |==X==|
|       Anna Pacanowska |  X  |     |  X  |  X  |  X  |  X  |  X  |
|       Bartosz Sobocki |  X  |  X  |  X  |     |     |     |  X  |
|    Mikołaj Stachnik   |     |     |     |     |     |     |     |
|       Marcin Szmagara |  X  |  X  |  X  |  X  |  X  |  X  |  X  |
|       Paweł Szmergała |  X  |  X  |  X  |  X  |  X  |     |  X  |
|      Krzysztof Zajdel |     |     |     |     |     |     |     |
|    Radosław Zazulczak |  X  |  X  |  X  |     |     |     | X   |
| Małgorzata Maciejewska|  X  |  X  |  X  |     |     |     |     |
:::

## Zadanie 1-1

:::warning
Autor: Małgorzata Maciejewska
:::

Wyjątki dzielą się na przerwania, pułapki, błędy i zatrzymania.

***Przerwania sprzętowe*** -- urządzenie wymagające obsługi procesora wystawia sygnał na linii przerwania, którą jest połączone z procesorem,  gdy chce być obsłużone.
Przykłady to naciśnięcie klawisza na klawiaturze, naciśnięcie przycisku na skanerze lub sygnał od układu scalnego (np. ugładu czasowego - timera)

***Wyjątek procesora*** -- nagła zmana w przepływie sterowania w odpowiedzi na zmianę stanu procesora (zdarzenie).
Przykłady to błąd dzielenia przez 0, błąd dostępu do strony pamięci, przepałnienie arytmetyczne.

***Pułapki*** -- zamierzone wyjątki, które występują jako wynik wykonanej instrukcji. Pułapki dostarczają interfejsu między użytkownikiem a jądrem -- wywołania systemowe.
Przykłady to czytanie pliku (read), stworzenie nowego procesu (fork) czy zakończenie aktualnego procesu (exit).







## Zadanie 1-2

:::warning
Autor: Andriy Bernad
:::
Podczas ładowania systemu jest inicjalizowana tablica wektorów przerwań (nr. przerwanie → mechanizm przetwarzania) potem jeżeli podczas wykonywania nastąpi event któremu odpowiada wyjątek k, wtedy k to indeks w rejestrze bazowym tablicy wyjątków skąd już mamy dostęp do samej tablicy wyjątków gdzie jest adres handlera.
Przed wywołaniem instrukcji procesor szuka odpowiedniego handlera.
Po obsłudze procesor pobiera ze stosu informacje o stanie systemu przed wyjątkiem i przywraca go lub kończy proces awaryjnie.
Obsługa systemowa musi mieć kontrolę nad wszystkimi procesami aby np. zakończyć działanie pewnego progeramu. Odrębny stos – żeby zapobiec utracie danych.![](https://i.imgur.com/LTLFRCO.png)![](https://i.imgur.com/pM8GOqq.png)


> [name=Krystian Bacławski] To jest ważna uwaga ode mnie!


## Zadanie 1-3

:::warning
Autor: Wiktoria Kuna 
:::

Słowniczek:
***sekcja*** - zawiera dane (np. .data .text, które zostaną wczytane do pamięci).
***segment*** - lista sekcij. 
***nagłówek programu*** - zbiór informacji o pliku ELF. Określa między innymi czy należy używać 32- czy 64-bitowych adresów, entry point (adres do pierwszej instrukcji), adres początku tabeli nagłówków, sekcji etc.

Składowe pliku wykonywalnego:

| ELF header           |
|:--------------------:|
| Segment header table |
| .init                |
| .text                |
| .rodata              |
| .data                |
| .bss                 |
| .symtab              |
| .debug               |
| .line                |
| .strtab              |
| Section header table |

.init - zawiera wykonywalne instrukcje, które zostaną wykonane przed wykonaniem docelowego kodu.
.text - sekcja zawierająca wykonywalny kod.
.data - zainicjalizowane dane z prawami do zapisu i odczytu.
.rodata - zainicjalizowane dane z prawami wyłącznie do odczytu.
.bss - niezainicjalizowane dane z prawami do zapisu/odczytu.
.symtab - tablica symboli.
.debug - sekcja zawierająca informacje dla debuggera.
.line - inforacje dla debuggera zawierające numery linii kojarzące kod programu z kodem maszynowym.
.strtab - zawiera napisy (stringi).

## Zadanie 1-4

:::danger
Autor: Marcin Szmagara 
:::

Stos w momencie wywołania `_start`:
```
wysokie adresy - niesprecyzowane
stringi środowiskowe, stringi będące argumentami, informacje dodatkowe
niesprecyzowane
null - końcówka wektora pomocniczego
elementy wektora pomocniczego
0
wskaźniki środowiskowe
0
wskaźniki na argumenty progamu
liczba argumentów progarmu
niskie adresy - niezdefiniowane
```
*Auxiliary vector*
Kiedy program jest uruchomiony otrzymuje informacje od systemu operacyjnego za pomocą wektora pomocniczego (auxiliary vector).
Niektóre informacje przekazywane można otrzymać innymi sposobami od jądra, a inne są zależne od platformy.

Wywołanie systemowe jest podobne do wywołania funkcji - argumenty w
`%rdi, %rsi, %rdx, ...`. W prze zawsze w rejestrach, nigdy na stosie.
Następnie wykonuje się polecenie `syscall`.
W API języka C zdefiniowane są poręczne wrappery funkcji `syscall`.
W `%rax` można otrzymać zwróconą wartość. Jeśli jest < 0, to wywołanie się nie powiodło. Błąd można odczytać z `errno`. Kod błędu należy interpretować w zależnośći od rodzaju wywołania systemowego (konkretne informacje w dokumentacji).

## Zadanie 1-5

:::warning
Autor: Paweł Szmergała 
:::
**tłumaczenie adresów** --- proces tłumaczenia adresów wirtualnych na adresy fizyczne.
**adres fizyczny** --- adres w pamięci RAM.
**adres wirtualny** --- adres w przestrzeni adresowej procesu.
**pamięć TLB** (*Translation Lookaside Buffer*) --- pamięć podręczna wykorzystywana w tłumaczeniu adresów. Dla najczęściej/ostatnio używanych adresów wirtualnych, przechowuje odpowiadające im adresy fizyczne w celu przyspieszenia dostępu do nich.
![](https://i.imgur.com/HsCNzlN.png)
##### Szkic algorytmu obliczania adresu fizycznego (PA) na podstawie adresu wirtualnego (VA)
* PPO = VPO (przesunięcie fragmentu strony jest takie samo w pamięci wirtualnej jak i fizycznej)
* Wyślij zapytanie o VPN do TLB.
* TLB hit => od razu dostajemy PPN.
* TLB miss =>
    * PT_begin = CR3 // rejestr procesora, wskazujący początek tablicy stron
    * Dla i = 1 do liczby poziomów tablicy stron:
        * Zajrzyj pod wpis wskazany przez VPNi w i-tej tablicy stron
        * Ustaw bit `A` tego wpisu, informujący o niedawnym dostępie do niego.
        * W przypadku braku uprawnień (bity `U/S`, `R/W`) => niepowodzenie
        * PT_begin = \*(PT_begin + VPNi)
    * PPN = PT_begin


## Zadanie 1-6

:::danger
Autor: Anna Pacanowska 
:::

**opendir**: open, fstat, brk
**readdir**: getdents
**closedir**: close
**printf**: write

**brk** zmienia lokalizacje program break - końca segmentu danych procesu. Zwiększenie segmentu alokuje pamięć do procesu, a zmniejszenie dealokuje. Jest używany przez opendir.

Fragment wyniku ltrace:
``` 
opendir("include" <unfinished ...>
SYS_open("include", 591872, 0731251000)                                                                                                            = 3
SYS_fstat(3, 0x7ffc07655040)                                                                                                                       = 0
SYS_brk(0)                                                                                                                                         = 0x23e8000
SYS_brk(0x2411000)                                                                                                                                 = 0x2411000
<... opendir resumed> )                                                                                                                            = 0x23e8010
readdir(0x23e8010 <unfinished ...>
SYS_getdents(3, 0x23e8040, 0x8000, 143)                                                                                                            = 80
<... readdir resumed> )                                                                                                                            = 0x23e8040
puts("apue.h" <unfinished ...>
SYS_fstat(1, 0x7ffc07654fa0)                                                                                                                       = 0
SYS_write(1, "apue.h\n", 7apue.h
)                                                                                                                        = 7
<... puts resumed> )                                                                                                                               = 7
readdir(0x23e8010)                                                                                                                                 = 0x23e8060
puts("." <unfinished ...>
SYS_write(1, ".\n", 2.
)                                                                                                                             = 2
<... puts resumed> )                                                                                                                               = 2
readdir(0x23e8010)                                                                                                                                 = 0x23e8078
puts(".." <unfinished ...>
SYS_write(1, "..\n", 3..
)                                                                                                                            = 3
<... puts resumed> )                                                                                                                               = 3
readdir(0x23e8010 <unfinished ...>
SYS_getdents(3, 0x23e8040, 0x8000, 404)                                                                                                            = 0
<... readdir resumed> )                                                                                                                            = 0
closedir(0x23e8010 <unfinished ...>
SYS_close(3)                                                                                                                                       = 0
<... closedir resumed> )                                                                                                                           = 0
exit(0 <unfinished ...>
SYS_exit_group(0 <no return ...>
```
gdb:
```
Catchpoint 1 (call to syscall brk), 0x00007ffff7b09e19 in __brk (addr=addr@entry=0x0) at ../sysdeps/unix/sysv/linux/x86_64/brk.c:31
31	../sysdeps/unix/sysv/linux/x86_64/brk.c: Nie ma takiego pliku ani katalogu.
(gdb) backtrace
#0  0x00007ffff7b09e19 in __brk (addr=addr@entry=0x0) at ../sysdeps/unix/sysv/linux/x86_64/brk.c:31
#1  0x00007ffff7b09edf in __GI___sbrk (increment=167936) at sbrk.c:43
#2  0x00007ffff7a948c9 in __GI___default_morecore (increment=<optimized out>) at morecore.c:47
#3  0x00007ffff7a8e635 in sysmalloc (nb=nb@entry=32832, av=av@entry=0x7ffff7dd1b20 <main_arena>) at malloc.c:2484
#4  0x00007ffff7a8f743 in _int_malloc (av=av@entry=0x7ffff7dd1b20 <main_arena>, bytes=bytes@entry=32816) at malloc.c:3827
#5  0x00007ffff7a92898 in __GI___libc_malloc (bytes=32816) at malloc.c:2913
#6  malloc_hook_ini (sz=32816, caller=<optimized out>) at hooks.c:32
#7  0x00007ffff7ad51ba in __alloc_dir (statp=0x7fffffffdeb0, flags=0, close_fd=true, fd=3) at ../sysdeps/posix/opendir.c:247
#8  opendir_tail (fd=3) at ../sysdeps/posix/opendir.c:145
#9  __opendir (name=<optimized out>) at ../sysdeps/posix/opendir.c:200
#10 0x00000000004009dc in main (argc=<optimized out>, argv=0x7fffffffe058) at 1_ls.c:11
```
## Zadanie 1-7

:::warning
Autor: Hubert Obrzut
:::

**standardowe wejście (stdin)** - standardowe źródło danych dla programów działających w konsoli na systemach UNIX
**standardowe wyjście (stdout)** - ujście danych dla programów działających w konsoli

Każdy proces po uruchomieniu jest związany z trzema deskryptorami plików - stdin(0), stdout(1), stderr(2).

**ścieżka** - nazwa pliku w systemi, unikatowa dla pliku, umożliwia lokalizację i dostęp do niego

Przykładowy output polecenia ```strace ./2_cat inputfile```:
```
execve("./2_cat2", ["./2_cat2", "inputfile"], 0x7fff8c371298 /* 36 vars */) = 0
brk(NULL)                               = 0x561d31814000
arch_prctl(0x3001 /* ARCH_??? */, 0x7ffc5cbbf330) = -1 EINVAL (Invalid argument)
access("/etc/ld.so.preload", R_OK)      = -1 ENOENT (No such file or directory)
openat(AT_FDCWD, "/etc/ld.so.cache", O_RDONLY|O_CLOEXEC) = 3
fstat(3, {st_mode=S_IFREG|0644, st_size=133315, ...}) = 0
mmap(NULL, 133315, PROT_READ, MAP_PRIVATE, 3, 0) = 0x7f94e0216000
close(3)                                = 0
openat(AT_FDCWD, "/usr/lib/libc.so.6", O_RDONLY|O_CLOEXEC) = 3
read(3, "\177ELF\2\1\1\3\0\0\0\0\0\0\0\0\3\0>\0\1\0\0\0\220\202\2\0\0\0\0\0"..., 832) = 832
pread64(3, "\6\0\0\0\4\0\0\0@\0\0\0\0\0\0\0@\0\0\0\0\0\0\0@\0\0\0\0\0\0\0"..., 784, 64) = 784
pread64(3, "\4\0\0\0\20\0\0\0\5\0\0\0GNU\0\2\0\0\300\4\0\0\0\3\0\0\0\0\0\0\0", 32, 848) = 32
pread64(3, "\4\0\0\0\24\0\0\0\3\0\0\0GNU\0\364[g\253(\257\25\201\313\250\344q>\17\323\262"..., 68, 880) = 68
fstat(3, {st_mode=S_IFREG|0755, st_size=2159552, ...}) = 0
mmap(NULL, 8192, PROT_READ|PROT_WRITE, MAP_PRIVATE|MAP_ANONYMOUS, -1, 0) = 0x7f94e0214000
pread64(3, "\6\0\0\0\4\0\0\0@\0\0\0\0\0\0\0@\0\0\0\0\0\0\0@\0\0\0\0\0\0\0"..., 784, 64) = 784
mmap(NULL, 1868448, PROT_READ, MAP_PRIVATE|MAP_DENYWRITE, 3, 0) = 0x7f94e004b000
mmap(0x7f94e0071000, 1363968, PROT_READ|PROT_EXEC, MAP_PRIVATE|MAP_FIXED|MAP_DENYWRITE, 3, 0x26000) = 0x7f94e0071000
mmap(0x7f94e01be000, 311296, PROT_READ, MAP_PRIVATE|MAP_FIXED|MAP_DENYWRITE, 3, 0x173000) = 0x7f94e01be000
mmap(0x7f94e020a000, 24576, PROT_READ|PROT_WRITE, MAP_PRIVATE|MAP_FIXED|MAP_DENYWRITE, 3, 0x1be000) = 0x7f94e020a000
mmap(0x7f94e0210000, 12960, PROT_READ|PROT_WRITE, MAP_PRIVATE|MAP_FIXED|MAP_ANONYMOUS, -1, 0) = 0x7f94e0210000
close(3)                                = 0
mmap(NULL, 8192, PROT_READ|PROT_WRITE, MAP_PRIVATE|MAP_ANONYMOUS, -1, 0) = 0x7f94e0049000
arch_prctl(ARCH_SET_FS, 0x7f94e0215580) = 0
mprotect(0x7f94e020a000, 12288, PROT_READ) = 0
mprotect(0x561d313e0000, 4096, PROT_READ) = 0
mprotect(0x7f94e0263000, 4096, PROT_READ) = 0
munmap(0x7f94e0216000, 133315)          = 0
openat(AT_FDCWD, "inputfile", O_RDONLY) = 3
read(3, "1 line\n2 line\n3 line\n", 4096) = 21
write(1, "1 line\n2 line\n3 line\n", 211 line
2 line
3 line
) = 21
read(3, "", 4096)                       = 0
exit_group(0)                           = ?
+++ exited with 0 +++
```
