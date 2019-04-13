// Marko Golovko
// Pracownia PO, piątek, s. 137
// L 3, z 2, Dictionary 
// Tests
// testy słownika
// 2018-03-16

using System;
using TestDict;
namespace TestL3Z2
{
    class Program
    {
        static void Main(string[] args)
        {
            Dictionary dict = new Dictionary();
            dict.Insert("Marko", "Golovko");
            Console.WriteLine(dict.ReturnValue("Marko"));
            dict.Delete("Marko");
            Console.WriteLine(dict.ReturnValue("Marko"));
            Console.WriteLine(dict.ReturnValue("Dima"));
            dict.Insert("Dima", "Pekun");
            Console.WriteLine(dict.ReturnValue("Dima"));
            Console.ReadLine();
        }
    }
}
