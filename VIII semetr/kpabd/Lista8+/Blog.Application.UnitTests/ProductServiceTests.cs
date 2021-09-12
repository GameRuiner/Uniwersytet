using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Shop.Domain.Model.Product;
using Shop.Domain.Model.Product.Repositories;
using Shop.ObjectMothers;
using Moq;

namespace Shop.Application.UnitTests
{
    [TestClass]
    public class ProductServiceTests
    {

        [TestMethod]
        public void CheckAddProduct()
        {
            Mock<IProductRepository> repositoryMock = new Mock<IProductRepository>();
            ShopService ss = new ShopService(repositoryMock.Object);

            Product product = ss.AddNewProduct("Bow", 100, "Good for novice");

            repositoryMock.Verify(k => k.Insert(product), Times.Once());
        }

        [TestMethod]
        public void CheckFindProducts()
        {
            Mock<IProductRepository> repositoryMock = new Mock<IProductRepository>();
            ShopService ss = new ShopService(repositoryMock.Object);

            var products = ss.GetAllProducts();

            repositoryMock.Verify(k => k.FindAll(), Times.Once());
        }

        [TestMethod]
        public void CheckGetBrand()
        {
            Mock<IBrandRepository> repositoryMock = new Mock<IBrandRepository>();
            ShopService ss = new ShopService(repositoryMock.Object);
            string brandName = "Hoyt";
            ss.GetBrand(brandName);

            repositoryMock.Verify(k => k.Find(brandName), Times.Once());
        }

        [TestMethod]
        public void CheckGetProperty()
        {
            Mock<IPropertyRepository> repositoryMock = new Mock<IPropertyRepository>();
            ShopService ss = new ShopService(repositoryMock.Object);
            string propertyName = "Color";
            string propertyValue = "Blue";
            ss.GetProperty(propertyName, propertyValue);

            repositoryMock.Verify(k => k.Find(propertyName, propertyValue), Times.Once());
        }
    }
}
