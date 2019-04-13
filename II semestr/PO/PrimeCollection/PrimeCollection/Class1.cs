//Marko Golovko
// Pracownia PO, piątek, s. 137
// L4, z2
// Prime Collection
// Implementacja klasa
// 2018-03-23
using System;
using System.Collections;
namespace PrimeCollection
{

    public class CollectionPrime : IEnumerable 
    {
       public Element current = new Element(2);

        IEnumerator IEnumerable.GetEnumerator()
        {
            return GetEnumerator();
        }
        public IEnumerator GetEnumerator()
        {
            return new PrimeEnum(current);
        }
    }


    class PrimeEnum : IEnumerator
    {
        Element current;
        public PrimeEnum(Element head)
        {
            this.current = head;
        }
        public Element next(Element e)
        {
            int current = e.value + 1;
            while (!isPrime(current))
            {
                current += 1;
            }
            Element NewElem = new Element(current);
            return NewElem;
        }


        // sprawdzanie czy liczba jest pierwsza 
        bool isPrime(int n)
        {
            if (n < 2)
            {
                return false;
            }
            if (n == 2)
            {
                return true;
            }
            for (int i = 2; i <= Math.Sqrt(n); i++)
            {
                if (n % i == 0)
                {
                    return false;
                }
            }
            return true;
        }


        //przesuwa na jeden element wprzód
        public bool MoveNext()
        {
            if (this.current.value == 2147483629) this.current = null;
            else this.current = this.next(current) ;
            return this.current != null;
        }


        // obecny element 
        public object Current
        {
            get
            {
                return current.value;
            }
        }


        //przeniesienie na początkowy element 
        public void Reset()
        {
            this.current = new Element(2);
        }
        public void Dispose() { }
    }


    // element listy
    public class Element
    {
        public int value;
        public Element next;
        public Element(int n)
        {
            value = n;
        }
    }
}

