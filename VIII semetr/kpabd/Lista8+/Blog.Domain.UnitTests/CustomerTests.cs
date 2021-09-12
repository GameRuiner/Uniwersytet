using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Shop.ObjectMothers;

namespace Shop.Domain.UnitTests
{
    [TestClass]
    public class CustomerTests
    {
        [TestMethod]
        public void CheckAddAddressToCustomer()
        {
            // Arrange
            var customer = CustomerObjectMother.CreateCustomerWithNoAddress();
            var address = CustomerObjectMother.CreateAddressWithCityWroclaw(1);
            customer.Address = address;


            // Assert
            Assert.IsTrue(customer.Address.City == "Wroclaw");
        }
    }
}
