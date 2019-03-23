// Marko Golovko
// Pracownia PO, piątek, s. 137
// L 3, z 1, lista leniwa 
// Lazy list
// testy listy leniwej
// 2018-03-16

using System;

namespace Lista2Zadanie4
{
    class Program
    {
        static void Main(string[] args)
        {
            ListaLeniwa lista = new ListaLeniwa();
            Console.WriteLine("Element pierwszy");
            Console.WriteLine(lista.element(1));
            Console.WriteLine("Rozmiar listy");
            Console.WriteLine(lista.size());
            Console.WriteLine("Element 38");
            Console.WriteLine(lista.element(38));
            Console.WriteLine("Rozmiar listy");
            Console.WriteLine(lista.size());
            Console.WriteLine("Element 40");
            Console.WriteLine(lista.element(40));
            Pierwsze lp = new Pierwsze();
            Console.WriteLine("Element 1 listy lizb pierwszych");
            Console.WriteLine(lp.element(1));
            Console.WriteLine("Rozmiar listy");
            Console.WriteLine(lp.size());
            Console.WriteLine("Element 38 listy lizb pierwszych");
            Console.WriteLine(lp.element(38));
            Console.WriteLine("Rozmiar listy");
            Console.WriteLine(lp.size());
            Console.WriteLine("Element 40 listy lizb pierwszych");
            Console.WriteLine(lp.element(40));
            Console.ReadKey();
        }
    }
}
