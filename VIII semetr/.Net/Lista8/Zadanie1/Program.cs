using System;
using System.Collections.Generic;
using System.Data.OleDb;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp3
{
    class Program
    {
        static void Main(string[] args)
        {
            string connectionString = "Provider=Microsoft.ACE.OLEDB.12.0;Data Source=D:\\Users\\MarkG\\uniwer\\VIII_semestr\\.Net\\Lista8\\Zadanie1\\Database11.accdb";
            using (OleDbConnection connection = new OleDbConnection(connectionString))
            using (var command = new OleDbCommand("select * from Table1", connection))
            {
                try
                {
                    connection.Open();
                    using (var reader = command.ExecuteReader())
                    {
                        while (reader.Read())
                        {
                            Console.WriteLine(string.Format("{0} {1} {2}", reader["ID"], reader["StudentName"], reader["Year"]));
                        }
                    }
                    
                }
                catch (Exception ex)
                {
                    Console.WriteLine(ex.Message);
                }
            }
            Console.ReadLine();
        }
    }
}
