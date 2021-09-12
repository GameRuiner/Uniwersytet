using Shop.Domain.Model.Customer;
using Shop.Domain.Model.Order;
using Shop.Domain.Model.Order.Repositories;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Shop.Infrastructure.Repositories
{
    public class OrderIM : IOrderRepository
    {
        private List<Order> orders = new List<Order>();

        public OrderIM()
        {
            orders = new List<Order>
            {
                new Order { Id = 1, Customer = new Customer(), Status = "created", CreatedTime = DateTime.Now, DeliveryType = "express",  PaymentType = "bankTransfer", OrderItems = new List<OrderItems>()},
                new Order { Id = 2, Customer = new Customer(), Status = "paid", CreatedTime = DateTime.Now, DeliveryType = "mail",  PaymentType = "creditCard", OrderItems = new List<OrderItems>()},
            };
        }

        public void Insert(Order order)
        {
            orders.Add(order);
        }

        public void Delete(int id)
        {
            foreach (var o in orders)
                if (o.Id == id)
                    orders.Remove(o);
        }

        public Order Find(int id)
        {
            foreach (var o in orders)
                if (o.Id == id)
                    return o;

            return null;
        }

        public List<Order> FindAll()
        {
            return orders;
        }
    }
}
