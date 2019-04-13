// Marko Golovko
// Pracownia PO, piątek, s. 137
// L 2, z 1, IntStream 
// IntStream
// testy ciągów
// 2018-03-16

using System;

namespace Lista2Zadanie1
{
    class Program
    {
        static void Main(string[] args)
        {
            IntStream x,y;
            PrimeStream px;
            RandomStream rx;
            x = new IntStream();
            y = new IntStream();
            Console.WriteLine("Stworzono dwa ciągi liczb naturalnych x i y");
            Console.WriteLine("Następny element x");
            Console.WriteLine(x.next());
            Console.WriteLine("Następny element y");
            Console.WriteLine(y.next());
            Console.WriteLine("Następny element y");
            Console.WriteLine(y.next());
            y.eos();
            Console.WriteLine("Następny element po zakończeniu ciagu y");
            y.next();
            Console.WriteLine(y.next());
            y.eos();
            y.reset();
            Console.WriteLine("Następny element po wyzerowaniu ciagu");
            Console.WriteLine(y.next());
            px = new PrimeStream();
            Console.WriteLine("Nowy ciąg liczb pierwszych");
            Console.WriteLine(px.next());
            Console.WriteLine(px.next());
            rx = new RandomStream();
            Console.WriteLine("Ciąg liczb losowych");
            Console.WriteLine(rx.next());
            Console.WriteLine(rx.next());
            RandomWordStream rws = new RandomWordStream();
            Console.WriteLine("Ciąg napisów o długościach kolejnych liczb pierwszych");
            Console.WriteLine(rws.next()); // zwraca losowy string o długości  2
            Console.WriteLine(rws.next()); // zwraca losowy string o długości 3
            Console.WriteLine(rws.next()); // zwraca losowy string o długości 5
            Console.ReadLine();
        }
    }
}
