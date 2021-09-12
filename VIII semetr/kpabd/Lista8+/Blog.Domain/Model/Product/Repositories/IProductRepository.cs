using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Shop.Domain.Model.Product.Repositories
{
    public interface IProductRepository
    {
        void Insert(Product product);

        void Delete(int id);

        Product Find(int id);

        List<Product> FindAll();
    }
}
