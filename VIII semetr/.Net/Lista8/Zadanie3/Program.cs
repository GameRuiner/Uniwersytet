using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            using (var context = new StudentCityContext())
            { 
                var newCity = new City() { Name = "Wroclaw" };
                var newStudent = new Student() { Name = "Andre", Surname = "Gordey", BirthDayDate = "1999", City = newCity };

                context.Student.Add(newStudent); // add

                context.SaveChanges();

                var query = context.Student;

                foreach (var student in query)
                {
                    Console.WriteLine(student.Name + " " + student.Surname);
                    if (student.Name == "Marko")
                    {
                        student.Name = "Mark"; // update
                    }
                    if (student.Name == "Andre")
                    {
                        context.Student.Remove(student); // delete
                    }
                    
                }

                context.SaveChanges();

                var query2 = context.City;

                foreach (var city in query2)
                {
                    Console.WriteLine(city.Name + " " + city.Id);
                }
            }
            Console.ReadLine();
        }
    }

    public class StudentCityContext : DbContext
    {
        public DbSet<Student> Student { get; set; }

        public DbSet<City> City { get; set; }
    }

    public class Student
    {
        public int Id { get; set; }

        public string Name { get; set; }

        public string Surname { get; set; }

        public string BirthDayDate { get; set; }

        public City City { get; set; }
    }

    public class City
    {
        public int Id { get; set; }

        public string Name { get; set; }

        public ICollection<Student> Students { get; set; }

    }
}
