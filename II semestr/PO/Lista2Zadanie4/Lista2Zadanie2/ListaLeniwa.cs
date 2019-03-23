// Marko Golovko
// Pracownia PO, piątek, s. 137
// L 3, z 1, lista leniwa 
// Lazy list
// class listy leniwej
// 2018-03-16

using System;
using System.Collections;
using System.Collections.Generic;
using System.Text;

namespace Lista2Zadanie4
{
    class ListaLeniwa
    {
        internal int length = 0;
        internal int current = 0;
        internal List<int> container = new List<int>();
        public virtual int element (int i)
        {
            if (this.length < i)
            {
                while (length < i)
                {
                    length += 1;
                    container.Add(current);
                    current += 1;
                }
                return current;
            }
            return container[i];
        }
        public virtual int size()
        {
            return length+1;
        }
    }
}
