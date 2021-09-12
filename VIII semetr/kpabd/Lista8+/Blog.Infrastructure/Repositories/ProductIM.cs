using Shop.Domain.Model.Product;
using Shop.Domain.Model.Product.Repositories;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Shop.Infrastructure.Repositories
{
    public class ProductIM : IProductRepository
    {
        private readonly List<Product> products = new List<Product>();

        public ProductIM()
        {
            products = new List<Product>
            {
                new Product { Id = 1, Name = "Bow", Brand = new Brand(), Price = 2800, Status = "available",  CreatedAt = DateTime.Now, Description = "Good bow for novice archers", Properties = new List<Property>() },
                new Product { Id = 2, Name = "Arrows", Brand = new Brand(), Price = 1750, Status = "out of stock",  CreatedAt = DateTime.Now, Description = "Good arrows for novice archers", Properties = new List<Property>() },
            };
        }

        public void Insert(Product product)
        {
            products.Add(product);
        }

        public void Delete(int id)
        {
            foreach (var p in products)
                if (p.Id == id)
                    products.Remove(p);
        }

        public Product Find(int id)
        {
            foreach (var p in products)
                if (p.Id == id)
                    return p;

            return null;
        }

        public List<Product> FindAll()
        {
            return products;
        }
    }
}
