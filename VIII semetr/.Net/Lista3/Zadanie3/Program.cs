using System;
using System.Text.RegularExpressions;
using System.Linq;
using System.IO;

namespace PPW3
{
    class Program
    {
        static void Main(string[] args)
        {
            var names = File.ReadLines("nazwiska.txt");
            var nums2 = from name in names
                        group name by name.Substring(0, 1) into letter
                        orderby letter.Key
                        select letter.Key;
            foreach (string num in nums2)
            {
                Console.WriteLine(num);
            }
        }
    }
}
