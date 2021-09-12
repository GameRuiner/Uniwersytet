using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Shop.Domain.Model.Customer.Repositories
{
    public interface ICustomerRepository
    {
        void Insert(Customer customer);

        void Delete(int id);

        Customer Find(int id);

        List<Customer> FindAll();
    }
}
