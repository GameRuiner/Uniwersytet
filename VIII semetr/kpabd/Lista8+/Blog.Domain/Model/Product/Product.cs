using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Shop.Domain.Model.Product
{
    public class Product
    {
        public virtual int Id { get; set; }

        public virtual string Name { get; set; }

        public virtual Brand Brand { get; set; }

        public virtual double Price { get; set; }

        public virtual string Status { get; set; }

        public virtual DateTime CreatedAt { get; set; }

        public virtual string Description { get; set; }

        public virtual IList<Property> Properties { get; set; }

    }
}
