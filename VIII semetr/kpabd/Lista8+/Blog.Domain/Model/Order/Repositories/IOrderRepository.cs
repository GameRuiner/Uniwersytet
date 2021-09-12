using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Shop.Domain.Model.Order.Repositories
{
    public interface IOrderRepository
    {
        void Insert(Order order);

        void Delete(int id);

        Order Find(int id);

        List<Order> FindAll();
    }
}
