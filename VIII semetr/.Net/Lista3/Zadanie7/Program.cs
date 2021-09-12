using System;
using System.Text.RegularExpressions;
using System.Linq;
using System.IO;
using System.Collections.Generic;

namespace PPW3
{
    class Program
    {
        static void Main(string[] args)
        {

            var item = new { Field1 = "The value", Field2 = 5 };
            List<dynamic> theList = new List<dynamic> { item };
            var listItem = new[] { item }.ToList();
            var list = "".Select(t => new { Field1 = "", Field2 = 0 }).ToList();
            theList.Add(item);
            theList.Add(3);
            listItem.Add(item);
            list.Add(item);
            foreach (var e in theList)
            {
                Console.WriteLine(e);
            }
            // W powyższym przykładzie możemy do theList dodawać nowe obiekty takiego samego typu.
            // Możemy i innego, nikt nie zabranial : )

        }
    }
}
