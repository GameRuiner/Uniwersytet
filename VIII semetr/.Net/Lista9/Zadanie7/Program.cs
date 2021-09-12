using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml;
using System.Xml.Linq;
using System.Xml.Schema;
using System.Xml.Serialization;

namespace ConsoleApp4
{
    class Program
    {
        static void Main(string[] args)
        {


            while (true)
            {
                Char c = Console.ReadLine()[0];
                XElement student = XElement.Load(@"D:\Users\MarkG\uniwer\VIII_semestr\.Net\Lista9\Zadanie7\student.xml");
                IEnumerable<string> studentsLastName = from s in student.Elements() where ((string)s?.Attribute("lastName"))[0] == c select (string)s.Attribute("lastName");
                foreach (var name in studentsLastName)
                {
                    Console.WriteLine(name);
                }
            }

            Console.ReadLine();
        }
    }
}
