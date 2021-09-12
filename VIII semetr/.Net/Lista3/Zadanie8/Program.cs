using System;
using System.Text.RegularExpressions;
using System.Linq;
using System.IO;
using System.Collections.Generic;

namespace PPW3
{
    class Program
    {
        public static Func<A, R> Y<A, R>(Func<Func<A, R>, Func<A, R>> f)
        {
            Func<A, R> g = null;
            g = f(a => g(a));
            return g;
        }

        static void Main(string[] args)
        {
            List<int> list = new List<int>() { 1, 2, 3, 4, 5, 6, 7};

            foreach (var item in
                list.Select(Y<int, int>(i => x => x <= 2 ? 1 : i(x - 1) + i(x - 2))))
            {
                Console.WriteLine(item);
            }
        }
    }
}
