//09.03.18
//Lista 2 Zadanie 1
// Marko Golovko


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
            Console.WriteLine(x.next());
            Console.WriteLine(y.next());
            Console.WriteLine(y.next());
            y.eos();
            y.next();
            Console.WriteLine(y.next());
            y.eos();
            y.reset();
            Console.WriteLine(y.next());
            px = new PrimeStream();
            Console.WriteLine(px.next());
            Console.WriteLine(px.next());
            rx = new RandomStream();
            Console.WriteLine(rx.next());
            Console.WriteLine(rx.next());
            RandomWordStream rws = new RandomWordStream();
            Console.WriteLine(rws.next()); // zwraca losowy string o d? 2
            Console.WriteLine(rws.next()); // zwraca losowy string o d? 3
            Console.WriteLine(rws.next());
            Console.ReadLine();
        }
    }
}
