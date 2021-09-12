using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Collections;   

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            Set s = new Set();
            s.Add(1);
            s.Add(1);
            s.Add(2);
            s.Add(3);
            s.Add(3);
            s.Remove(1);
            foreach (int e in s ){
                Console.WriteLine(e);
            }
            s.Clear();
            foreach (int e in s)
            {
                Console.WriteLine(e);
            }

            Console.ReadLine();
        }
    }

    class Set : ArrayList
    {
        public override int Add(object value)
        {
            if (!this.Contains(value))
            {
                return base.Add(value);
            } else
            {
                return -1;
            }
            
        }
    }
}
