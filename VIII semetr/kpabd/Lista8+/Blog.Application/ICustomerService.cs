using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Shop.Domain.Model.Customer;
using Shop.Domain.Model.Order;
using Shop.Domain.Model.Product;

namespace Shop.Application
{
    interface ICustomerService
    {
        Customer CreateNewCustomer(string FullName, string email);

        void SetCustomerAddress(Customer c, string street, string city, string state, string country, string zipCode);

        void SetCustomerEmail(Customer c, string email);
    }
}
