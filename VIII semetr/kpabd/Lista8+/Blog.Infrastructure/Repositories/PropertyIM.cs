using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Shop.Domain.Model.Product;
using Shop.Domain.Model.Product.Repositories;

namespace Shop.Infrastructure.Repositories
{
    public class PropertyIM : IPropertyRepository
    {
        private readonly List<Property> properties = new List<Property>();

        public PropertyIM()
        {
            properties = new List<Property>
            {
                new Property { Id = 1, Name = "Color", Value = "Blue" },
                new Property { Id = 2, Name = "Color", Value = "Red" },
                new Property { Id = 3, Name = "Color", Value = "Green" },
                new Property { Id = 4, Name = "Size", Value = "M" },
                new Property { Id = 5, Name = "Size", Value = "S" },
                new Property { Id = 6, Name = "Size", Value = "L" },
            };
        }

        public void Insert(Property property)
        {
            properties.Add(property);
        }

        public void Delete(string name)
        {
            foreach (var p in properties)
                if (p.Name == name)
                    properties.Remove(p);
        }

        public Property Find(string name)
        {
            foreach (var p in properties)
                if (p.Name == name)
                    return p;
            return null;
        }

        public List<Property> FindAll()
        {
            return properties;
        }

        public void Delete(string name, string value)
        {
            foreach (var p in properties)
                if (p.Name == name && p.Value == value)
                    properties.Remove(p);
        }

        public Property Find(string name, string value)
        {
            foreach (var p in properties)
                if (p.Name == name && p.Value == value)
                    return p;
            return null;
        }
    }
}
