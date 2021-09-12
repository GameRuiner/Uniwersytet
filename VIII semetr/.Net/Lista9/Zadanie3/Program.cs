using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml;
using System.Xml.Linq;
using System.Xml.Schema;

namespace ConsoleApp4
{
    class Program
    {
        static void Main(string[] args)
        {
            XmlReader rd = XmlReader.Create(@"D:\Users\MarkG\uniwer\VIII_semestr\.Net\Lista9\Zadanie1\student.xml");
            XmlSchemaSet schema = new XmlSchemaSet();
            schema.Add("", @"D:\Users\MarkG\uniwer\VIII_semestr\.Net\Lista9\Zadanie2\student.xsd");
            XDocument doc = XDocument.Load(rd);
            doc.Validate(schema, validationEventHandler);
        }

        static void validationEventHandler(object sender, ValidationEventArgs e)
        {
            XmlSeverityType type = XmlSeverityType.Warning;
            if (Enum.TryParse<XmlSeverityType>("Error", out type))
            {
                if (type == XmlSeverityType.Error)
                    throw new Exception(e.Message);
            }
        }
    }
}
