// Marko Golovko
// Pracownia PO, piątek, s. 137
// L 3, z 1, lista leniwa 
// Lazy list
// testy listy leniwej liczb pierwszych 
// 2018-03-16

using System;
using System.Collections.Generic;
using System.Text;

namespace Lista2Zadanie4
{
    class Pierwsze : ListaLeniwa
    {
        public override int element(int i)
        {
            while (length < i)
            {
                current += 1;
                while (!isPrime(current))
                {
                    current += 1;
                }
                length += 1;
                container.Add(current);

            }
            return container[i-1];
        }
        public override int size()
        {
            return length;
        }
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
    }
}
