using Shop.Domain.Model.Customer;
using Shop.Domain.Model.Customer.Repositories;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Shop.Infrastructure.Repositories
{
    public class CustomerIM : ICustomerRepository
    {
        private readonly List<Customer> customers = new List<Customer>();

        public CustomerIM()
        {
            customers = new List<Customer>
            {
                new Customer { Id = 1, FullName = "Marko Golovko", Email = "test@mail.pl", CreateAccountDate = DateTime.Now, Address = new Address() },
                new Customer { Id = 2, FullName = "Andriy Gordey", Email = "test2@mail.pl", CreateAccountDate = DateTime.Now, Address = new Address() },
            };
        }

        public void Insert(Customer customer)
        {
            customers.Add(customer);
        }

        public void Delete(int id)
        {
            foreach (var c in customers)
                if (c.Id == id)
                    customers.Remove(c);
        }

        public Customer Find(int id)
        {
            foreach (var c in customers)
                if (c.Id == id)
                    return c;

            return null;
        }

        public List<Customer> FindAll()
        {
            return customers;
        }
    }
}
