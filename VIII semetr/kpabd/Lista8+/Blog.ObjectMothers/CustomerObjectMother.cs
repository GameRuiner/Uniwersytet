using Shop.Domain.Model.Customer;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Shop.ObjectMothers
{
    public class CustomerObjectMother
    {
        public static Customer CreateCustomerWithNoAddress()
        {
            Customer c = new Customer { Id = 1, FullName = "Marko Golovko", Email = "test@mail.pl", CreateAccountDate = DateTime.Now, Address = new Address() };

            return c;
        }

        public static Address CreateAddressWithCityWroclaw(int id)
        {
            return new Address { Id = id, Street="Sunny", City="Wroclaw", Country="Poland", State= "Lower Silesian", ZipCode="53-360" };
        }
    }
}
