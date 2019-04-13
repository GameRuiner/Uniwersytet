// Marko Golovko
// Pracownia PO, piątek, s. 137
// L 2, z 4, lista leniwa
// Pierwsze
// klasa listy piewrwszych liczb
// 2018-03-09

using System;
using System.Collections.Generic;
using System.Text;

namespace Lista2Zadanie2
{
    class Pierwsze : ListaLeniwa
    {

        //oblicza i element listy
        public override int element(int i)
        {
            while (length <= i)
            {
                current += 1;
                while (!isPrime(current))
                {
                    current += 1;
                }
                length += 1;
                container.Add(current);

            }
            return container[i];
        }


        //test czy liczba jest pierwsza 
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
