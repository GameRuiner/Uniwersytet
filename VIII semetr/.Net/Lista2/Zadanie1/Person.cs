using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ListaPPW2
{
    class Person
    {
        private int Age { get; set; }

        public string name { get; set; }

        public Person()
        {
            Age = 11;
            name = "Ian";

        }

        private void Greeting()
        {
            Console.WriteLine("I greet you!");
        }

       
    }
}
