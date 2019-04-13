// Marko Golovko
// Pracownia PO, piątek, s. 137
// L 2, z 1, IntStream 
// PrimeStream
// ciąg liczb pierwszych 
// 2018-03-16

using System;
using System.Collections.Generic;
using System.Text;

namespace Lista2Zadanie1
{
    public class PrimeStream : IntStream
    {
        //zwraca kolejny element ciagu
        override public int next()
        {
            if (!b) { current += 1; }
            while (!isPrime(current))
            {
                current += 1;
            }
            if (current == 2147483629)
            {
                eos();
            }
            return current;
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
            for(int i = 2; i<=Math.Sqrt(n); i++)
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
