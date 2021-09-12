using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            Complex z = new Complex(4, 3);
            Console.WriteLine(String.Format("{0}", z));
            Console.WriteLine(String.Format("{0:d}", z));
            Console.WriteLine(String.Format("{0:w}", z));
            Console.ReadLine();
        }
    }

    class Complex : IFormattable
    {
        private int _real;
        private int _imaginary;

        public Complex(int r, int i) {
            this._real = r;
            this._imaginary = i;
        }

        public override string ToString()
        {
            return ToString(null);
        }

        public string ToString(string format)
        {
            return ToString(format, null);
        }

        public string ToString(string format, IFormatProvider formatProvider)
        {
            if (formatProvider == null) formatProvider = CultureInfo.CurrentCulture;

            switch ( format)
            {
                case "w":
                    return $"[{this._real},{this._imaginary}]";
                default:
                    return $"{this._real}+{this._imaginary}i";
            }
        }
    }
}
