using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PPWLista1
{
    class Program
    {
        static void Main(string[] args)
        {
            int count = 0;
            for (int i = 1; i <= 100000; i++)
            {
                int sum = 0;
                bool good_num = true;
                foreach (char c in i.ToString()) {
                    int digit = (c - '0');
                    if (digit == 0 || i % digit != 0)
                    {
                        good_num = false;
                        break;
                    }
                    sum += digit;
                }
                if (good_num && i % sum == 0)
                {
                    Console.WriteLine(i);
                    count += 1;
                }
            }
            Console.WriteLine($"Dobrych liczb jest {count}");
            Console.ReadLine();
        }
    }
}
