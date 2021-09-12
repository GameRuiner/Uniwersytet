using System;
using System.Collections.Generic;
using System.Data.OleDb;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp3
{
    class Program
    {
        static void Main(string[] args)
        {
            string connectionString = @"server=DESKTOP-ARVMIUC;database=Test;integrated security=true";

            using (var context = new DataClasses1DataContext(connectionString))
            {
                var city = new Miejscowosc() { Nazwa = "Dnepr" };
                var newStudent = new Student() { Imię = "Liza", Nazwisko = "Shmidt", Miejscowosc = city };
                context.Students.InsertOnSubmit(newStudent); //insert
                context.SubmitChanges();

                foreach (var student in context.Students)
                {
                    Console.WriteLine($"{student.Imię} {student.Nazwisko}");
                    if (student.Imię.Trim() == "Marko")
                    {
                        student.Nazwisko = "Pawlow"; // update
                        context.SubmitChanges();
                    }
                    if (student.DataUrodzenia.Trim() == "1999")
                    {
                        context.Students.DeleteOnSubmit(student); //delete
                        context.SubmitChanges();
                    }
                }
            }
           
            Console.ReadLine();
        }
    }
}
