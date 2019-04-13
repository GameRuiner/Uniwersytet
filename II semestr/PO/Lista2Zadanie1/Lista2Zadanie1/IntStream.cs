using System;
using System.Collections.Generic;
using System.Text;

// Marko Golovko
// Pracownia PO, piątek, s. 137
// L 2, z 1, IntStream 
// IntStream
// ciąg liczb naturalnych
// 2018-03-16

namespace Lista2Zadanie1
{
    public class IntStream
    {
        internal int current = 0;
        internal bool b = false;

        //zwraca kolejny element ciagu
        virtual public int next()
        {
            if (!b)
            {
                return ++current;
            }
            else
            {
                return current;
            }
        }


        //ustawia koniec ciągu
        public bool eos()
        {
            b = true;
            return b;
        }

        //ustawia ciag na początek
        public void reset()
        {
            b = false;
            current = 0;
        }
            
    }
}
