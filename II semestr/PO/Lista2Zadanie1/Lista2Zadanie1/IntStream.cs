using System;
using System.Collections.Generic;
using System.Text;

namespace Lista2Zadanie1
{
    class IntStream
    {
        internal int current = 0;
        internal bool b = false;
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
        public bool eos()
        {
            b = true;
            return b;
        }
        public void reset()
        {
            current = 0;
        }
            
    }
}
