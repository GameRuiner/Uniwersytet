// Marko Golovko
// Pracownia PO, piątek, s. 137
// L3, z2, słownik 
// Dictionary
// test
// 2018-03-16
using System;
namespace TestDict
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
