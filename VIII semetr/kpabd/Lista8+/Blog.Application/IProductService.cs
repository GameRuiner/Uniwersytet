using Shop.Domain.Model.Customer;
using Shop.Domain.Model.Order;
using Shop.Domain.Model.Product;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Shop.Application
{
    interface IProductService
    {
        IList<Product> GetAllProducts();

        Product AddNewProduct(string name, double price, string description);

        Brand GetBrand(string name);

        Property GetProperty(string name, string value);
    }
}
