using System;
using System.IO;
using System.Net.Sockets;
using System.Net;
using System.Threading.Tasks;
using System.Globalization;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            WriteInfo(new CultureInfo("en-gb", false));
            WriteInfo(new CultureInfo("de-de", false));
            WriteInfo(new CultureInfo("fr-fr", false));
            WriteInfo(new CultureInfo("ru-ru", false));
            WriteInfo(new CultureInfo("ar-sa", false));
            WriteInfo(new CultureInfo("cs-cz", false));
            WriteInfo(new CultureInfo("pl-pl", false));

            Console.ReadLine();
        }

        static void WriteInfo(CultureInfo ci)
        {
            Console.WriteLine(ci.Name);
            foreach (var month in ci.DateTimeFormat.MonthNames)
            {
                Console.Write(month + " ");
            }
            Console.WriteLine();
            foreach (var month in ci.DateTimeFormat.AbbreviatedMonthNames)
            {
                Console.Write(month + " ");
            }
            Console.WriteLine();
            foreach (var day in ci.DateTimeFormat.DayNames)
            {
                Console.Write(day + " ");
            }
            Console.WriteLine();
            foreach (var day in ci.DateTimeFormat.AbbreviatedDayNames)
            {
                Console.Write(day + " ");
            }
            Console.WriteLine();
            Console.WriteLine(DateTime.Now.Date.ToString(ci));
            Console.WriteLine();
        }
    }
}
