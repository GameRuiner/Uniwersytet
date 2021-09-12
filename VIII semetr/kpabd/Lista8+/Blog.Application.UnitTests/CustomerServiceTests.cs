using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Shop.Domain.Model.Customer.Repositories;
using Shop.Domain.Model.Customer;
using Shop.ObjectMothers;
using Moq;

namespace Shop.Application.UnitTests
{
    [TestClass]
    public class CustomerServiceTests
    {
        [TestMethod]
        public void CheckIfNewCustomerAdded()
        {
            Mock<ICustomerRepository> repositoryMock = new Mock<ICustomerRepository>();
            ShopService ss = new ShopService(repositoryMock.Object);

            Customer customer = ss.CreateNewCustomer("Marko Golovko", "mail@example.com");

            repositoryMock.Verify(k => k.Insert(customer), Times.Once());
        }

        [TestMethod]
        public void CheckSetCustomerAddress()
        {
            Mock<ICustomerRepository> repositoryMock = new Mock<ICustomerRepository>();
            ShopService ss = new ShopService(repositoryMock.Object);
            var customer = CustomerObjectMother.CreateCustomerWithNoAddress();

            string street = "Sliczna";
            string city = "Wroclaw";
            string state = "Lower Silesia";
            string country = "Poland";
            string zipCode = "50-363";

            ss.SetCustomerAddress(customer, street, city, state, country, zipCode);

            Assert.AreEqual(customer.Address.Street, street);
            Assert.AreEqual(customer.Address.City, city);
            Assert.AreEqual(customer.Address.State, state);
            Assert.AreEqual(customer.Address.Country, country);
            Assert.AreEqual(customer.Address.ZipCode, zipCode);
        }

        [TestMethod]
        public void CheckSetCustomerEmail()
        {
            Mock<ICustomerRepository> repositoryMock = new Mock<ICustomerRepository>();
            ShopService ss = new ShopService(repositoryMock.Object);
            var customer = CustomerObjectMother.CreateCustomerWithNoAddress();

            string email = "newemail@example.com";

            ss.SetCustomerEmail(customer, email);

            Assert.AreEqual(customer.Email, email);
        }
    }
}
