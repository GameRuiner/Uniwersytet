// Marko Golovko
// Pracownia PO, piątek, s. 137
// L 3, z 1, lista wiązana 
// Linked list
// testy listy wiązanej
// 2018-03-16

// Marko Golovko
// Pracownia PO, piątek, s. 137
// L 4, z 1, lista wiązana 
// Kolekcja IEnumerable
// testy
// 2018-03-23

using System;
using Lista3Zadanie1;
namespace TestL3Z1
{
    class Program
    {
        static void Main(string[] args)
        {
            LinkedList<int> TestList = new LinkedList<int>();
            Console.WriteLine("Stworzona pusta lista");
            Console.WriteLine("Test czy lista jest pusta");
            TestList.RemoveFirst();
            TestList.RemoveFirst();
            TestList.RemoveFirst();
            TestList.RemoveFirst();
            Console.WriteLine(TestList.IsEmpty());
            TestList.AddAtStart(1);
            TestList.AddAtStart(2);
            TestList.Append(3);
            Console.WriteLine("Dodałem 1 i 2 na początek, i 3 na koniec");
            TestList.PrintList();
            TestList.RemoveLast();
            Console.WriteLine("Usunąlem ostatni element");
            TestList.PrintList();
            TestList.RemoveFirst();
            Console.WriteLine("Usunąlem pierwszy element");
            TestList.PrintList();
            Console.WriteLine("Test czy lista jest pusta");
            Console.WriteLine(TestList.IsEmpty());
            TestList.AddAtStart(1);
            TestList.AddAtStart(2);
            TestList.Append(3);
            Console.WriteLine("Długość");
            Console.WriteLine(TestList.Length);
            Console.WriteLine("Początek listy");
            foreach (int e in TestList)
            Console.WriteLine(e);
            Console.WriteLine("Koniec listy");
            Console.WriteLine(TestList.ToString());
            Console.WriteLine(TestList[3]);
            Console.ReadLine();

        }
    }
}
