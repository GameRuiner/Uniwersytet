using Shop.Domain.Model.Customer;
using Shop.Domain.Model.Order;
using Shop.Domain.Model.Product;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Shop.ObjectMothers
{
    public class ProductObjectMother
    {
        public static Product CreateProductWithNoPropertiesAndNoBrand()
        {
            Product p = new Product { Id = 1, Name = "Bow", Brand = new Brand(), Price = 2800, Status = "available", CreatedAt = DateTime.Now, Description = "Good bow for novice archers", Properties = new List<Property>() };

            return p;
        }

        public static Brand CreateBrandWithNameHoyt()
        {
            return new Brand { Name = "Hoyt", Description = "Hoyt Archery is an American manufacturer" };
        }

        public static Property CreatePropertyColorWithValueBlue()
        {
            return new Property { Name = "Color", Value = "Blue" };
        }
    }
}
