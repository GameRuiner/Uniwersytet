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

            StringBuilder sb2 = new StringBuilder();
            using (StringWriter sw = new StringWriter(sb2))
            using (XmlTextWriter xtw = new XmlTextWriter(sw))
            {
                xtw.WriteStartDocument();
                xtw.WriteStartElement("Person");
                xtw.WriteAttributeString("Firstname", "Marko");
                xtw.WriteAttributeString("LastName", "Golovko");

                xtw.WriteStartElement("Address");
                xtw.WriteAttributeString("City", "Wroclaw");
                xtw.WriteEndElement();

                xtw.WriteStartElement("Address");
                xtw.WriteAttributeString("City", "Kharkiv");
                xtw.WriteEndElement();

                xtw.WriteEndElement();
                xtw.WriteEndDocument();
            }

            Console.WriteLine(sb2);

            XmlReader rd = XmlReader.Create(@"D:\Users\MarkG\uniwer\VIII_semestr\.Net\Lista9\Zadanie1\student.xml");
            while (rd.Read())
            {
                Console.WriteLine(rd.ReadOuterXml());
            }

            Console.ReadLine();
        }
    }

}
