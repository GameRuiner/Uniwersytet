using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Shop.Domain.Model.Order.Repositories;
using Shop.ObjectMothers;
using Moq;
using Shop.Domain.Model.Product;
using Shop.Domain.Model.Order;

namespace Shop.Application.UnitTests
{
    [TestClass]
    public class ShopServiceTests
    {
        [TestMethod]
        public void CheckAddToOrder()
        {
            ShopService ss = new ShopService();

            Product product = ProductObjectMother.CreateProductWithNoPropertiesAndNoBrand();
            Order order = OrderObjectMother.CreateOrderWithNoItems();
            int quantitity = 2;

            var orderItems = ss.AddToOrder(product, order, quantitity);

            Assert.AreEqual(orderItems.Product, product);
            Assert.AreEqual(orderItems.Quantitity, quantitity);
            Assert.AreEqual(order.OrderItems.Contains(orderItems), true);
        }

        [TestMethod]
        public void CheckSetBrand()
        {
            ShopService ss = new ShopService();

            var product = ProductObjectMother.CreateProductWithNoPropertiesAndNoBrand();
            var brand = ProductObjectMother.CreateBrandWithNameHoyt();

            ss.SetBrand(product, brand);

            Assert.AreEqual(product.Brand, brand);
        }

        [TestMethod]
        public void CheckAddProperty()
        {
            ShopService ss = new ShopService();

            var product = ProductObjectMother.CreateProductWithNoPropertiesAndNoBrand();
            var property = ProductObjectMother.CreatePropertyColorWithValueBlue();

            ss.AddProperty(product, property);

            Assert.AreEqual(product.Properties.Contains(property), true);
        }
    }
}