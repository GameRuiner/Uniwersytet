# SO Lista 2 (1,2,3,4,5)
## Zadanie 1
**Stan procesu**:

* **Uruchomiony**: ta wartość stanu odpowiada dwóm stanom. Uruchomiony proces jest albo wykonywanym lub gotowym do wykonania.

* **Przerywalny sen**: jest to stan zablokowany, w którym proces oczekuje na zdarzenie, takie jak zakończenie operacji I/O, dostępność zasobu lub sygnał z innego procesu.

* **Nieprzerywalny sen**: to kolejny stan zablokowany. Różnica między tym a stanem przerywalnym to że w stanie nieprzerywalnym, proces oczekuje bezpośrednio na warunki sprzętowe i dlatego nie obsługuje żadnych sygnałów. 

* **Zatrzymany**: proces został zatrzymany i można go wznowić tylko poprzez pozytywne działanie z innego procesu. Na przykład debugowany proces umieszczony do stanu zatrzymany. 

* **Zombie**: Proces został zakończony, ale z jakiegoś powodu nadal jest w tabeli procesów.

![](https://i.imgur.com/p8vZ2GW.png)

Gdy proces **blokuje** sygnał, system operacyjny nie dostarcza sygnału, dopóki proces nie odblokuje sygnału. Ale gdy proces **ignoruje** sygnał, sygnał jest dostarczany, a proces odrzuca go.

Sygnał SIGKILL nie może zostać zablokowany ani zignorowany.

## Zadanie 2


### WinNT

Procesy są tworzone z obiektów sekcji, z których każdy opisuje obiekt pamięci wspierany przez plik na dysku. Kiedy proces jest tworzony, proces tworzenia otrzymuje uchwyt, który pozwala mu modyfikować nowy proces poprzez mapowanie sekcji, przydzielanie pamięci wirtualnej, zapisywanie parametrów i danych środowiskowych, duplikowanie deskryptorów plików do swojej tabeli uchwytów i tworzenie wątków.

Obiekt sekcji reprezentuje sekcję pamięci, którą można udostępniać. Proces może używać obiektu sekcji do współdzielenia części swojej przestrzeni adresowej pamięci (sekcji pamięci) z innymi procesami. Obiekty sekcji zapewniają również mechanizm, za pomocą którego proces może mapować plik do swojej przestrzeni adresowej pamięci.

### Linux

Wywołanie systemowe fork tworzy dokładną kopię oryginalnego procesu.

#### fork

![](https://i.imgur.com/YQo83bI.png)

#### execve

![](https://i.imgur.com/exDq6JF.png)

Po forku Linux tworzy kopię listy obszarów dla procesu potomnego, ale konfiguruje rodzica i potomka tak, aby wskazywały te same tabele stron. Obszary są oznaczone jako do odczytu/zapisu, ale same strony są oznaczone jako tylko do odczytu. Jeśli którykolwiek z procesów próbuje pisać na stronie, pojawia się protection fault i jądro widzi, że obszar jest zapisywalny, ale strona nie jest zapisywalna, więc przekazuje procesowi kopię strony i oznacza ją do odczytu/zapisu. Ten mechanizm polega na implementacji **kopiowania przy zapisie**.

## Zadanie 3

### fork. Zasoby dziedziczone przez dziecko
* Otwarte pliki
* Rzeczywisty identyfikator użytkownika, rzeczywisty identyfikator grupy, efektywny identyfikator użytkownika i efektywny identyfikator grupy
* Terminal sterujący
* Bieżący katalog roboczy
* Katalog główny
* Środowisko
* Dołączone segmenty pamięci współdzielonej
* Mapowania pamięci

### execve. Zasoby dziedziczone z wywołującego procesu
* Identyfikator procesu i identyfikator parent procesu 
* Prawdziwy identyfikator użytkownika i prawdziwy identyfikator grupy
* Terminal sterujący
* Czas do budzika
* Bieżący katalog roboczy
* Katalog główny
* Maska sygnału procesowego
* Oczekujące sygnały
* Nice value

Sygnał przechwytywany przez proces wywołujący exec nie może zostać przechwycony przez tę samą funkcję w nowym programie, ponieważ adres funkcji przechwytującej sygnał w programie wywołującym prawdopodobnie nie ma znaczenia w nowym pliku programu, który jest wykonywany.

**Wyłapywanie sygnału**. Aby to zrobić, mówimy jądru, aby wywołał naszą funkcję zawsze, gdy pojawia się sygnał. W naszej funkcji możemy zrobić wszystko, co chcemy, aby obsłużyć warunek.

### Zadanie 4
kill,pkill,xkill - SIGTERM
fg/bg

SigQ: To pole zawiera dwie liczby oddzielone ukośnikiem, które odnoszą się do sygnałów w kolejce dla rzeczywistego identyfikatora użytkownika tego procesu.

SigPnd,ShdPnd: Maska sygnałów w oczekiwaniu

SigBlk, SigIgn, SigCgt: Maski wskazujące na blokowane, ignorowane i przechwytywane sygnały


### Zadanie 5
**Sieroty** to te procesy, które nadal działają, mimo że ich parent proces został zakończony.

**Żniwiarz** (reaper) pełni rolę init dla swoich procesów potomnych. Kiedy proces zostanie osierocony (tj. jego bezpośredni rodzic zostanie zakończony), wówczas ten proces zostanie przekazany najbliższemu wciąż żyjącemu żniwiarzowi.
<details>
  <summary>Stary kod</summary>

```c=
#include "csapp.h"

static pid_t spawn(void (*fn)(void)) {
  pid_t pid = Fork();
  if (pid == 0) {
    fn();
    printf("(%d) I'm done!\n", getpid());
    exit(EXIT_SUCCESS);
  }
  return pid;
}

static void grandchild(void) {
  printf("(%d) Waiting for signal!\n", getpid());
  pause();
  printf("(%d) Got the signal!\n", getpid());
}

static void child(void) {
  pid_t pid;
  setpgid(0,0);
  pid = spawn(grandchild);
  printf("(%d) Grandchild (%d) spawned!\n", getpid(), pid);
  exit(0);
}

static void ps(void) {
  char *const parmList[] = {"/bin/ps", "-o", "pid,ppid,pgrp,stat,cmd", NULL};
  execve("/bin/ps", parmList, 0);

}


int main(void) {
#ifdef LINUX
  Prctl(PR_SET_CHILD_SUBREAPER, 1);
#endif
  printf("(%d) I'm a reaper now!\n", getpid());

  pid_t pid;
  int status;

  pid = spawn(child);
  
  waitpid(pid, &status, 0);
  
  spawn(ps);
  
  waitpid(0, &status, 0);
  
  kill(-pid,2);
  
  waitpid(-1, &status, 0);
  
  if (WIFSIGNALED(status)) {
    printf("(%d) Exited due to receiving signal %d\n", getpid(), WTERMSIG(status));
  }
  

  return EXIT_SUCCESS;
}
```
</details>

<details>
  <summary>Stary kod z komentarzem</summary>

```c=

  pid_t ps_pid = spawn(ps);
  
  waitpid(ps_pid, &status, 0); // czekamy na ps
  
  kill(-pid,SIGINT); // wysyłamy SIGINT do grupy z numerem pid(dziecka)
  
  waitpid(-pid, &status, 0); // czekamy na zakończenie procesu grupy pid(dziecka)
  
  if (WIFSIGNALED(status)) { //zwraca prawdę, jeśli proces potomny został zakończony sygnałem.
    // wypisuje numer sygnalu
    printf("%d Exited due to receiving signal %d. Status %d \n", getpid(), WTERMSIG(status),WEXITSTATUS(status))
```

```=
(4334) I'm a reaper now!
(4335) Grandchild (4336) spawned!
(4336) Waiting for signal!
  PID  PPID  PGRP STAT CMD
 2509  2500  2509 Ss   bash
 4334  2509  4334 S+   ./reaper
 4336  4334  4335 S    ./reaper
 4337  4334  4334 R+   /bin/ps -o pid,ppid,pgrp,stat,cmd
(4334) Exited due to receiving signal 2. Status 0

```
Na wydruku jest widoczny main proces w linii 6.
I proces grandchild w linii 7.
W linii 9 jest wpisany numer sygnału i kod wyjścia wnuku.

**Kod wyjścia** wykonanego polecenia to wartość zwracana przez wywołanie systemowe waitpid.
Polecenie, które kończy pracę z zerowym kodem wyjścia, wywołując exit (3) lub _exit (2), lub powracając z funkcji main ().

SIGINT ma kod 2
  
</details>

### Zadanie 6




