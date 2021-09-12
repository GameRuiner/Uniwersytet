using Shop.Domain.Model.Customer;
using Shop.Domain.Model.Order;
using Shop.Domain.Model.Product;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Shop.Application
{
    public interface IOrderService
    { 
        Order CreateNewOrder(string status, string delivery_type, string payment_type);

        void ChooseDeliveryType(Order o, string d);

        void ChoosePaymentType(Order o, string p);
    }
}
