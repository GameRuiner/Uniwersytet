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
    public interface IShopService
    {

        OrderItems AddToOrder(Product p, Order o, int q);

        void SetBrand(Product p, Brand b);

        void AddProperty(Product prod, Property prop);

    }
}
