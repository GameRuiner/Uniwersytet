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

            XmlDocument doc = new XmlDocument();
            var person = doc.CreateElement("Person");
            person.SetAttribute("FirstName", "Marko");
            person.SetAttribute("LastName", "Golovko");
            var address1 = doc.CreateElement("Address");
            person.AppendChild(address1);
            address1.SetAttribute("City", "Wroclaw");
            var address2 = doc.CreateElement("Address");
            person.AppendChild(address2);
            address2.SetAttribute("City", "Kharkiv");
            doc.AppendChild(person);
            Console.WriteLine(doc.OuterXml);

            Console.ReadLine();
        }
    }
}
