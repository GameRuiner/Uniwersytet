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
            var p = new Person()
            {
                Addresses = new Address[]
                {
                    new Address()
                    {
                        City = "Wroclaw",
                        ZipCode = "50-464"
                    },
                    new Address()
                    {
                        City = "Kharkiv",
                        ZipCode = "50-018"
                    }
                },
                FirstName = "Marko",
                LastName = "Golovko",
                CoursesGrades = new Course[]
                {
                    new Course() { Name = ".Net", Grade = 5},
                    new Course() { Name = "BD", Grade = 5},
                    new Course() { Name = "ADS", Grade = 3}
                }
            };

            StringBuilder sb = new StringBuilder();
            using (StringWriter sw = new StringWriter())
            {
                XmlSerializer xs = new XmlSerializer(typeof(Person));
                xs.Serialize(sw, p);
                Console.WriteLine(sw);
            }
   
        }
    }

    public class Person
    {
        [XmlElement]
        public Address[] Addresses { get; set; }

        [XmlAttribute]
        public string FirstName { get; set; }

        [XmlAttribute]
        public string LastName { get; set; }

        [XmlElement]
        public Course[] CoursesGrades;
    }

    public class Address
    {
        [XmlAttribute]
        public string City { get; set; }

        [XmlAttribute]
        public string ZipCode { get; set; }
    }

   public class Course
    {
        [XmlAttribute]
        public string Name { get; set; }

        [XmlAttribute]
        public int Grade { get; set; }

    }
}
