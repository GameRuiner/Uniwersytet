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
            var logs = File.ReadLines("log.txt");
            var res = logs.GroupBy(log => log.Split(" ")[1])
                          .Select(group => new
                          {
                              Ip = group.Key,
                              Count = group.Count()
                          })
                          .OrderBy(x => -x.Count)
                          .Take(3)
                          .Select(x => x.Ip + " " + x.Count);
            foreach (var s in res)
            {
                Console.WriteLine(s);
            }
            
        }
    }
}
