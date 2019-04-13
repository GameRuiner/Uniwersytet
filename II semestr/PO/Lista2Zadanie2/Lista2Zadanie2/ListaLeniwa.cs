// Marko Golovko
// Pracownia PO, piątek, s. 137
// L 2, z 4, lista leniwa
// ListaLeniwa
// klasa listy
// 2018-03-09

using System;
using System.Collections;
using System.Collections.Generic;
using System.Text;

namespace Lista2Zadanie2
{
    class ListaLeniwa
    {
        internal int length = 0;
        internal int current = 0;
        internal List<int> container = new List<int>();


        //oblicza i element listy
        public virtual int element (int i)
        {
            if (this.length <= i)
            {
                while (length <= i)
                {
                    length += 1;
                    container.Add(current);
                    current += 1;
                }
                return current;
            }
            return container[i];
        }

        //zwraca rozmiar listy
        public int size()
        {
            return length-1;
        }
    }
}
