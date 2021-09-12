using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Shop.Domain.Model.Product.Repositories
{
    public interface IBrandRepository
    {
        void Insert(Brand property);

        void Delete(string name);

        Brand Find(string name);

        List<Brand> FindAll();
    }
}
