using Shop.Domain.Model.Customer;
using Shop.Domain.Model.Order;
using Shop.Domain.Model.Product;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Shop.ObjectMothers
{
    public class OrderObjectMother
    {
        public static Order CreateOrderWithNoItems()
        {
            Order o = new Order { Id = 1, Customer = new Customer(), Status = "created", CreatedTime = DateTime.Now, DeliveryType = "express", PaymentType = "bankTransfer", OrderItems = new List<OrderItems>() };

            return o;
        }

        public static OrderItems CreateOrderItemWith2Quantitity(int id)
        {
            return new OrderItems { Id = id, Product = new Product(), Quantitity = 2 };
        }
    }
}
