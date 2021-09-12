using System;
using System.Reflection;
using System.Collections;
using System.Collections.Generic;

namespace ListaPPW2
{
    class Program
    {
        static void Main(string[] args)
        {
            List<int> list = new List<int>();
            list.Add(2);
            list.Add(3);
            list.Add(4);
            list = list.ConvertAll(x => x * x);
            list = list.FindAll(x => x < 10);
            list.ForEach(x => Console.WriteLine(x));
            list.Add(-3);
            list.Add(-4);
            list.RemoveAll(x => x > 0);
            list.Add(33);
            list.Add(-34);
            list.Sort((a,b) => b-a); //descending order
            foreach (int x in list)
            {
                Console.WriteLine(x);
            }
        }
    }
}
