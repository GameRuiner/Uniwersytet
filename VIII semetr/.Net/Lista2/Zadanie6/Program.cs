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
            list = ListHelper.ConvertAll<int, int>(list, x => x * x);
            list = ListHelper.FindAll<int>(list, x => x < 1000);
            list.Add(2);
            list.Add(-22);
            int count = ListHelper.RemoveAll<int>(list, x => x > 5);
            list.Add(-23);
            list.Add(23);
            ListHelper.Sort(list, (a, b) => a - b);
            ListHelper.ForEach<int>(list, Console.WriteLine);
        }
    }
}
