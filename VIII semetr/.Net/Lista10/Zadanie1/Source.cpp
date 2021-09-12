/*
*
* Tworzenie grafiki za pomocą GDI
*
*/
#include <windows.h>
#include <string.h>
#include <cmath> 
/* Deklaracja wyprzedzająca: funkcja obsługi okna */
LRESULT CALLBACK WindowProcedure(HWND, UINT, WPARAM, LPARAM);
/* Nazwa klasy okna */
char szClassName[] = "PRZYKLAD";

int WINAPI WinMain(HINSTANCE hThisInstance, HINSTANCE hPrevInstance,
	LPSTR lpszArgument, int nFunsterStil)
{
	HWND hwnd; /* Uchwyt okna */
	MSG messages; /* Komunikaty okna */
	WNDCLASSEX wincl; /* Struktura klasy okna */
	/* Klasa okna */
	wincl.hInstance = hThisInstance;
	wincl.lpszClassName = (LPCWSTR)szClassName;
	wincl.lpfnWndProc = WindowProcedure; // wskaźnik na funkcję obsługi okna
	wincl.style = CS_DBLCLKS;
	wincl.cbSize = sizeof(WNDCLASSEX);
	/* Domyślna ikona i wskaźnik myszy */
	wincl.hIcon = LoadIcon(NULL, IDI_APPLICATION);
	wincl.hIconSm = LoadIcon(NULL, IDI_APPLICATION);
	wincl.hCursor = LoadCursor(NULL, IDC_ARROW);
	wincl.lpszMenuName = NULL;
	wincl.cbClsExtra = 0;
	wincl.cbWndExtra = 0;
	/* Jasnoszare tło */
	wincl.hbrBackground = (HBRUSH)GetStockObject(LTGRAY_BRUSH);
	/* Rejestruj klasę okna */
	if (!RegisterClassEx(&wincl)) return 0;
	/* Twórz okno */
	hwnd = CreateWindowEx(
		0,
		(LPCWSTR)szClassName,
		(LPCWSTR)"PRZYKLAD",
		WS_OVERLAPPEDWINDOW,
		CW_USEDEFAULT,
		CW_USEDEFAULT,
		512,
		512,
		HWND_DESKTOP,
		NULL,
		hThisInstance,
		NULL
	);
	ShowWindow(hwnd, nFunsterStil);
	/* Pętla obsługi komunikatów */
	while (GetMessage(&messages, NULL, 0, 0))
	{
		/* Tłumacz kody rozszerzone */
		TranslateMessage(&messages);
		/* Obsłuż komunikat */
		DispatchMessage(&messages);
	}
	/* Zwróć parametr podany w PostQuitMessage( ) */
	return messages.wParam;
}
int xSize, ySize;
double fun1(double x);
double fun2(double x);
/* Tę funkcję woła DispatchMessage( ) */
LRESULT CALLBACK WindowProcedure(HWND hwnd, UINT message,
	WPARAM wParam, LPARAM lParam)
{
	char sText[] = "Przykład 1, witam";
	HDC hdc; // kontekst urządzenia
	int i;
	PAINTSTRUCT ps;
	RECT r;
	HPEN hPen;
	HPEN hPen2;
	HBRUSH hBrush;
	switch (message)
	{
	case WM_DESTROY:
		PostQuitMessage(0);
		break;
	case WM_SIZE:
		xSize = LOWORD(lParam);
		ySize = HIWORD(lParam);
		GetClientRect(hwnd, &r);
		InvalidateRect(hwnd, &r, 1);
		break;
	case WM_PAINT:
		hdc = BeginPaint(hwnd, &ps);
		// osie
		hPen2 = CreatePen(PS_DASH, 5, RGB(255, 255, 255));
		SelectObject(hdc, hPen2);
		MoveToEx(hdc, 0, ySize / 2, NULL);
		LineTo(hdc, xSize, ySize / 2);
		MoveToEx(hdc, xSize / 2, 0, NULL);
		LineTo(hdc, xSize / 2, ySize);
		DeleteObject(hPen2);
		// f(x) = |x|
		hPen2 = CreatePen(PS_SOLID, 3, RGB(0, 255, 0));
		SelectObject(hdc, hPen2);
		MoveToEx(hdc, xSize/2 -100, ySize/2 - (int)fun2(-100), NULL);
		for (i = -100; i < 100; i++)
		{
			LineTo(hdc, xSize / 2 + i, ySize / 2 - (int)fun2(i));
		}
		DeleteObject(hPen2);
		// f(x) = x^2
		hPen2 = CreatePen(PS_DASHDOT, 3, RGB(255, 0, 0));
		SelectObject(hdc, hPen2);
		MoveToEx(hdc, xSize / 2 - 100, ySize / 2 - (int)fun1(-100), NULL);
		for (i = -100; i < 100; i++)
		{
			LineTo(hdc, xSize / 2 + i, ySize / 2 - (int)fun1(i));
		}
		DeleteObject(hPen2);
		EndPaint(hwnd, &ps);
		break;
	default:
		return DefWindowProc(hwnd, message, wParam, lParam);
	}
	return 0;
}

double fun1(double x) {
	return std::abs(x);
}

double fun2(double x) {
	return x * x;
}