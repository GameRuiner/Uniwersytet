using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Shop.ObjectMothers;

namespace Shop.Domain.UnitTests
{
    [TestClass]
    public class OrderTests
    {
        [TestMethod]
        public void CheckAddProductToOrder()
        {
            // Arrange
            var order = OrderObjectMother.CreateOrderWithNoItems();
            var orderItem = OrderObjectMother.CreateOrderItemWith2Quantitity(1);
            order.OrderItems.Add(orderItem);


            // Assert
            Assert.IsTrue(order.OrderItems.Count == 1);
        }
    }
}
