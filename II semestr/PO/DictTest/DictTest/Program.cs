// Marko Golovko
// Pracownia PO, piątek, s. 137
// L3, z2, słownik 
// Dictionary
// test
// 2018-03-16

using System;
using MyDictionary;
namespace DictTest
{
    class Program
    {
        static void Main(string[] args)
        {
            Dictionary dict = new Dictionary();
            dict.Insert("Marko", "Golovko");
            Console.WriteLine(dict.ReturnValue("Marko"));
            dict.Delete("Marko");
            dict.ReturnValue("Marko");
            dict.ReturnValue("Dima");
            dict.Insert("Dima", "Pekun");
            Console.WriteLine(dict.ReturnValue("Dima"));
            Console.ReadLine();
        }
    }
}
