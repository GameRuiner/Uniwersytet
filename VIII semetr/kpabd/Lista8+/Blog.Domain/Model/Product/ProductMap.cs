using FluentNHibernate.Mapping;
using Shop.Domain.Model.Product;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Shop.Infrastructure
{
    public class ProductMap : ClassMap<Product>

    {

        public ProductMap()

        {

            Id(x => x.Id).GeneratedBy.Increment(); ;

            Map(x => x.Name);

            Map(x => x.Brand.Id);

            Map(x => x.Price);

            Map(x => x.Status);

            Map(x => x.Description);

            HasMany(x => x.Properties);

            Table("Product");

        }

    }
}
