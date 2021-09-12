using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Shop.Domain.Model.Product;
using Shop.Domain.Model.Product.Repositories;

namespace Shop.Infrastructure.Repositories
{
    public class BrandIM : IBrandRepository
    {
        private readonly List<Brand> brands = new List<Brand>();

        public BrandIM()
        {
            brands = new List<Brand>
            {
                new Brand { Id = 1, Name = "Hoyt", Description = "Hoyt Archery is an American manufacturer of recurve and compound bows located in Salt Lake City" },
                new Brand { Id = 2, Name = "MK Archery", Description = "Find the greatest selection of Archery Equipment" },
            };
        }

        public void Insert(Brand brand)
        {
            brands.Add(brand);
        }

        public void Delete(string name)
        {
            foreach (var b in brands)
                if (b.Name == name)
                    brands.Remove(b);
        }

        public Brand Find(string name)
        {
            foreach (var b in brands)
                if (b.Name == name)
                    return b;
            return null;
        }

        public List<Brand> FindAll()
        {
            return brands;
        }

    }
}
