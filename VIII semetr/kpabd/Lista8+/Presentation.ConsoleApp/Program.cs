using Shop.Application;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Presentation.ConsoleApp
{
    class Program
    {
        static void Main(string[] args)
        {
            ShopService shopService = new ShopService();

            var product = shopService.AddNewProduct("Bow", 100, "Good bow");
            var property1 = shopService.GetProperty("Color", "Blue");
            var property2 = shopService.GetProperty("Size", "M");
            shopService.AddProperty(product, property1);
            shopService.AddProperty(product, property2);
            shopService.AddNewProduct(product);
            Console.WriteLine("hi");
            Console.ReadLine();
        }
    }
}
