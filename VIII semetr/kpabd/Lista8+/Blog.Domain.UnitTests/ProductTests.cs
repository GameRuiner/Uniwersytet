using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Shop.ObjectMothers;

namespace Shop.Domain.UnitTests
{
    [TestClass]
    public class ProductTests
    {
        [TestMethod]
        public void CheckSetBrandToProduct()
        {
            // Arrange
            var product = ProductObjectMother.CreateProductWithNoPropertiesAndNoBrand();
            var brand = ProductObjectMother.CreateBrandWithNameHoyt(1);
            product.Brand = brand;


            // Assert
            Assert.IsTrue(product.Brand.Name == "Hoyt");
        }

        [TestMethod]
        public void CheckPropertyToProduct()
        {
            // Arrange
            var product = ProductObjectMother.CreateProductWithNoPropertiesAndNoBrand();
            var property = ProductObjectMother.CreatePropertyColorWithValueBlue(1);
            product.properties.Add(property);


            // Assert
            Assert.IsTrue(product.properties.Count == 1);
        }
    }
}
