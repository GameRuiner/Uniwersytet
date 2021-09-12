using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Shop.Domain.Model.Product.Repositories
{
    public interface IPropertyRepository
    {
        void Insert(Property property);

        void Delete(string name);
        void Delete(string name, string value);

        Property Find(string name, string value);

        List<Property> FindAll();
    }
}
