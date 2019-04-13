// Marko Golovko
// Pracownia PO, piątek, s. 137
// L 2, z 4, lista leniwa
// ListaLeniwa
// testy
// 2018-03-09

using System;

namespace Lista2Zadanie2
{
    class Program
    {
        static void Main(string[] args)
        {
            ListaLeniwa lista = new ListaLeniwa();
            Console.WriteLine(lista.element(40));
            Console.WriteLine(lista.size());
            Console.WriteLine(lista.element(38));
            Console.WriteLine(lista.size());
            Console.WriteLine(lista.element(40));
            Pierwsze lp = new Pierwsze();
            Console.WriteLine(lp.element(40));
            Console.WriteLine(lp.size());
            Console.WriteLine(lp.element(38));
            Console.WriteLine(lp.size());
            Console.WriteLine(lp.element(40));
            Console.ReadKey();
        }
    }
}
