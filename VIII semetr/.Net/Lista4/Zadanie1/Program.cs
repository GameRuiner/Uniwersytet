using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace Lista4
{
    class Program
    {
        static int Foo(int x, int y)
        {
            for( int i = 0; i < 1000000; i++)
            {
                x += 1;
                y -= 1;
            }
            return x + y;
        }
        static dynamic Bar(dynamic x, dynamic y)
        {
            for (int i = 0; i < 1000000; i++)
            {
                x += 1;
                y -= 1;
            }
            return x + y;
        }
        static void Main(string[] args)
        {
            Thread.Sleep(2000);   
            DateTime Start = DateTime.Now;
            Foo(5, 10);
            DateTime End = DateTime.Now;
            TimeSpan Czas = Start - End;
            Console.WriteLine(Czas);
            Start = DateTime.Now;
            Bar(5, 10);
            End = DateTime.Now;
            Czas = Start - End;
            Console.WriteLine(Czas);
            Console.ReadLine();
            //-00:00:00.0097827
            //-00:00:00.0812701
            // Widać, że dynamiczne typowanie jest bardzo wolniejsze

        }
    }
}
