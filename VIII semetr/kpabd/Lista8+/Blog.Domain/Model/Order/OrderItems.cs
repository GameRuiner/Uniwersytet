using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Shop.Domain.Model.Product;

namespace Shop.Domain.Model.Order
{
    public class OrderItems
    {
        public int Id { get; set; }

        public Product.Product Product { get; set; }

        public int Quantitity { get; set; }
    }
}
