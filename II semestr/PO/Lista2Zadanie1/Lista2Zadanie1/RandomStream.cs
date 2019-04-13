// Marko Golovko
// Pracownia PO, piątek, s. 137
// L 2, z 1, IntStream 
// RandomStream
// ciąg losowych liczb 
// 2018-03-16

using System;
using System.Collections.Generic;
using System.Text;

namespace Lista2Zadanie1
{
    class RandomStream : IntStream
    {
        //zwraca kolejny element ciagu
        override public int next()
        {
            Random rnd = new Random();
            return rnd.Next(0, Int32.MaxValue);
        }
    }
}
