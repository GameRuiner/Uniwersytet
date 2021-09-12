using FluentNHibernate.Mapping;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Shop.Domain.Model.Product
{
    public class PropertyMap : ClassMap<Property>
    {
        public PropertyMap()

        {

            Id(x => x.Id).GeneratedBy.Increment(); ;

            Map(x => x.Name);

            Map(x => x.Value);

            Table("Property");

        }

    }
}

