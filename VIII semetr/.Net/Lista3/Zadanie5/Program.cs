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
            var accounts = File.ReadLines("account.txt");
            var people = File.ReadLines("person.txt");
            var res = people.Join(
                        accounts,
                        person => person.Split(' ')[2],
                        account => account.Split(' ')[0],
                        (person, account) => person + " " + account.Split(' ')[1]);
            foreach (var s in res)
            {
                Console.WriteLine(s);
            }
            
        }
    }
}
