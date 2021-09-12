using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Collections;
using System.IO;
using System.Security.Cryptography;
using System.IO.Compression;
using System.Threading;
using System.Collections.Concurrent;

namespace ConsoleApp1
{
    class Program
    {
        public static ConcurrentQueue<Customer> queue = new ConcurrentQueue<Customer>();
        public static AutoResetEvent customerCame = new AutoResetEvent(false);
        static void Main(string[] args)
        {
            Random rand = new Random();
            new Thread(Barber.CutHair).Start();
            Thread.Sleep(200);
            for(int i = 0; i < 10; i++)
            {
                int id = i;
                Thread.Sleep(rand.Next(1000, 3500));
                Customer c = new Customer() { Name = "Bob" + id };
                queue.Enqueue(c);

                if (queue.Count == 1)
                {
                    c.WakeUpBarber();
                }
            }
            Console.ReadLine();
        }
    }
    class Customer
    {
        public string Name { get; set; }

        public void WakeUpBarber()
        {
            Program.customerCame.Set();
        }

    }

    class Barber
    {
        public static void CutHair()
        {
            while (!Program.queue.IsEmpty)
            {                
                Program.queue.TryDequeue(out Customer c);
                Thread.Sleep(1500);
                Console.WriteLine("Haircut is done " + c.Name);
            }
            Console.WriteLine("Barber going to sleep");
            GoToSleep();

        }

        static void GoToSleep()
        {
            Program.customerCame.WaitOne();
            Console.WriteLine("Customer came");
            CutHair();
        }
    }
}
