using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Shop.Domain.Model.Order.Repositories;
using Shop.ObjectMothers;
using Moq;

namespace Shop.Application.UnitTests
{
    [TestClass]
    public class OrderServiceTests
    {
        [TestMethod]
        public void CheckInsertMethodCalled()
        {
            Mock<IOrderRepository> repositoryMock = new Mock<IOrderRepository>();
            ShopService ss = new ShopService(repositoryMock.Object);

            var order = ss.CreateNewOrder("created", "express", "bankTransfer");

            repositoryMock.Verify(k => k.Insert(order), Times.Once());
        }

        [TestMethod]
        public void CheckChooseDeliveryType()
        {
            Mock<IOrderRepository> repositoryMock = new Mock<IOrderRepository>();
            ShopService ss = new ShopService(repositoryMock.Object);

            var order = OrderObjectMother.CreateOrderWithNoItems();
            string deliveryType = "newDeliveryType";
            ss.ChooseDeliveryType(order, deliveryType);

            Assert.AreEqual(order.DeliveryType, deliveryType);
        }

        [TestMethod]
        public void CheckChoosePaymentType()
        {
            Mock<IOrderRepository> repositoryMock = new Mock<IOrderRepository>();
            ShopService ss = new ShopService(repositoryMock.Object);

            var order = OrderObjectMother.CreateOrderWithNoItems();
            string paymentType = "newPaymentType";
            ss.ChoosePaymentType(order, paymentType);

            Assert.AreEqual(order.PaymentType, paymentType);
        }
    }
}
