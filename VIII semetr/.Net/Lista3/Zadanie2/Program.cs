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
            var nums = File.ReadLines("liczby.txt").Select(int.Parse);
            var nums2 = from num in nums
                        where num > 100
                        orderby -num
                        select num;
            foreach (int num in nums2)
            {
                Console.WriteLine(num);
            }
            // W Where, OrderBy korzystamy z uogólnionego typu funkcyjnego Func
            var nums3 = nums.Where(x => x > 100).OrderBy(x => -x).Select(x => x);
            foreach (int num in nums2)
            {
                Console.WriteLine(num);
            }
        }
    }
}
