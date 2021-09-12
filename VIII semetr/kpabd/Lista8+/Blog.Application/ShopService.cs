using Shop.Domain.Model.Customer;
using Shop.Domain.Model.Customer.Repositories;
using Shop.Domain.Model.Order;
using Shop.Domain.Model.Order.Repositories;
using Shop.Domain.Model.Product;
using Shop.Domain.Model.Product.Repositories;
using Shop.Infrastructure.Repositories;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Shop.Application
{
    public class ShopService : IShopService, ICustomerService, IOrderService, IProductService
    {
        private readonly IProductRepository productRepository;
        private readonly IOrderRepository orderRepository;
        private readonly ICustomerRepository customerRepository;
        private readonly IBrandRepository brandRepository;
        private readonly IPropertyRepository propertyRepository;


        public ShopService()
        {
            //productRepository = new ProductIM();
            productRepository = new ProductNH();
            orderRepository = new OrderIM();
            customerRepository = new CustomerIM();
            brandRepository = new BrandIM();
            propertyRepository = new PropertyIM();
        }

        public ShopService(IProductRepository productRepository)
        {
            this.productRepository = productRepository;
        }

        public ShopService(IOrderRepository orderRepository)
        {
            this.orderRepository = orderRepository;
        }

        public ShopService(ICustomerRepository customerRepository)
        {
            this.customerRepository = customerRepository;
        }

        public ShopService(IBrandRepository brandRepository)
        {
            this.brandRepository = brandRepository;
        }

        public ShopService(IPropertyRepository propertyRepository)
        {
            this.propertyRepository = propertyRepository;
        }

        public IList<Product> GetAllProducts()
        {
            return productRepository.FindAll();
        }

        public Product AddNewProduct(string name, double price, string description)
        {
            Product p = new Product() { 
                Name = name, 
                Price = price, 
                Description = description, 
                CreatedAt = DateTime.Now, 
                Status = "available" };
            productRepository.Insert(p);
            return p;
        }

        public Product AddNewProduct(Product p)
        {
            productRepository.Insert(p);
            return p;
        }

        public Order CreateNewOrder(string status, string delivery_type, string payment_type)
        {
            Order o = new Order() { CreatedTime = DateTime.Now, 
                                    DeliveryType = delivery_type, 
                                    PaymentType = payment_type, 
                                    OrderItems = new List<OrderItems>() };
            orderRepository.Insert(o);
            return o;
        }

        public void SetBrand(Product p, Brand b)
        {
            p.Brand = b;
        }

        public void AddProperty(Product prod, Property prop)
        {
            prod.Properties.Add(prop);
        }

        public OrderItems AddToOrder(Product p, Order o, int q)
        {
            OrderItems oi = new OrderItems { Product = p, Quantitity = q };
            o.OrderItems.Add(oi);
            return oi;
        }

        public void ChooseDeliveryType(Order o, string d)
        {
            o.DeliveryType = d;
        }

        public void ChoosePaymentType(Order o, string p)
        {
            o.PaymentType = p;
        }

        public Customer CreateNewCustomer(string FullName, string email)
        {
            Customer c = new Customer() { FullName = FullName, Email = email, CreateAccountDate = DateTime.Now };
            customerRepository.Insert(c);
            return c;
        }

        public void SetCustomerAddress(Customer c, string street, string city, string state, string country, string zipCode)
        {
            Address a = new Address() { City = city, Country = country, State = state, Street = street, ZipCode = zipCode };
            c.Address = a;
        }

        public void SetCustomerEmail(Customer c, string email)
        {
            c.Email = email;
        }

        public Brand GetBrand(string name)
        {
            return brandRepository.Find(name);
        }

        public Property GetProperty(string name, string value)
        {
            return propertyRepository.Find(name, value);
        }
    }
}
