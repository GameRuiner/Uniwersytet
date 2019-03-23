using System;
using System.Collections.Generic;
using System.Text;

namespace Lista2Zadanie1
{
    class RandomStream : IntStream
    {
       override public int next()
        {
            Random rnd = new Random();
            return rnd.Next(0, Int32.MaxValue);
        }
    }
}
