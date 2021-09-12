using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Shop.Domain.Model.Customer;

namespace Shop.Domain.Model.Order
{
    public class Order
    {
        public int Id { get; set; }

        public Customer.Customer Customer { get; set; }

        public string Status { get; set; }

        public DateTime CreatedTime { get; set; }

        public string DeliveryType { get; set; }

        public string PaymentType { get; set; }

        public IList<OrderItems> OrderItems { get; set; }
    }
}
