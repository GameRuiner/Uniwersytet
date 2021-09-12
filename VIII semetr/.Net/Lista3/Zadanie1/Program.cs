using System;
using System.Text.RegularExpressions;
using System.Linq;

namespace PPW3
{
    public static class StringExtensions
    {
        public static bool IsPalindrome(this string s)
        {
            //s = new Regex("[^a-zA-Z]").Replace(s, "").ToLower();
            s = new Regex("[^AaĄąBbCcĆćDdEeĘęFfGgHhIiJjKkLlŁłMmNnŃńOoÓóPpRrSsŚśTtUuWwYyZzŹźŻż]").Replace(s, "").ToLower();
            return s.SequenceEqual(s.Reverse());
        }
    }

    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Hello World!".IsPalindrome());
            string s = "Kobyła ma mały bok.";
            Console.WriteLine(s.IsPalindrome());
        }
    }
}
