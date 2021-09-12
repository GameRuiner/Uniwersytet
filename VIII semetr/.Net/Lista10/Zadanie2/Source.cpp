/*
*
* Tworzenie grafiki za pomocą GDI
*
*/
#define _USE_MATH_DEFINES
#include <windows.h>
#include <string.h>
#include <cmath> 
#include <synchapi.h>
#include <iostream>
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
	int direction = 165;
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
		// circle
		r.left = xSize/2-50;
		r.top = ySize / 2 - 50;;
		r.right = xSize / 2;
		r.bottom = ySize / 2 ;
		Ellipse(hdc, r.left, r.top, r.right, r.bottom);
		for (int i = 0; ; i++)
		{	
			Sleep(140);
			HBRUSH hBrush = CreateSolidBrush(RGB(255, 255, 255));
			FillRect(hdc, &r, hBrush);
			DeleteObject(hBrush);
			if (r.right >= xSize || r.left <= 0)
			{
				if (direction <= 180)
				{
					direction = 180 - direction;
				}
				else {
					direction = 540 - direction;
				}
				
			}
			if (r.top <= 0 || r.bottom >= ySize)
			{
				direction = abs(360 - direction);
			}
			double rad = M_PI * direction / 180;
			r.left += 10*cos(rad);
			r.right += 10*cos(rad);
			r.top -= 10 * sin(rad);
			r.bottom -= 10 * sin(rad);
			Ellipse(hdc, r.left, r.top, r.right, r.bottom);
		}
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