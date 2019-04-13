//Marko Golovko
// Pracownia PO, piątek, s. 137
// L 4, z 2
// Prime Collection
// Test
// 2018-03-23

using System;
using PrimeCollection;
namespace TestPrimeColl
{
    class Program
    {
        static void Main(string[] args)
        {
            CollectionPrime pc = new CollectionPrime();
            foreach (int p in pc)
            {
                if (p > 100) break;
                Console.WriteLine(p);
            }
            Console.ReadKey();
        }
    }
}
