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
            string[] fileEntries = Directory.GetFiles(".");
            long num = fileEntries.Aggregate<string, long, long>(0, (sum, file) => sum += new FileInfo(file).Length, sum => sum);
            Console.WriteLine(num);
            
        }
    }
}
