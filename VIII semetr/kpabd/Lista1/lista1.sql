USE AdventureWorksLT2019;

/* ex1 */
SELECT DISTINCT City FROM SalesLT.Address
RIGHT JOIN SalesLT.SalesOrderHeader
ON SalesLT.Address.AddressID = SalesLT.SalesOrderHeader.ShipToAddressID 
ORDER BY City;

/* ex2 */
SELECT ProductModel.Name, COUNT(ProductModel.ProductModelID) as Amount FROM SalesLT.ProductModel
JOIN SalesLT.Product
On SalesLT.Product.ProductModelID = ProductModel.ProductModelID
GROUP BY ProductModel.Name
HAVING COUNT(ProductModel.ProductModelID) > 1;

/*
SELECT NAME FROM SalesLT.Product
WHERE ProductModelID = 1
*/

/* ex3 */

/*
SELECT SalesPerson, COUNT(CustomerID) 
FROM SalesLT.Customer
Group By SalesPerson;


SELECT City, COUNT(Customer.CustomerID) 
FROM SalesLT.Customer
JOIN SalesLT.CustomerAddress
ON SalesLT.Customer.CustomerID = SalesLT.CustomerAddress.CustomerID
JOIN SalesLT.Address
On SalesLT.CustomerAddress.AddressID = SalesLT.Address.AddressID
Group By City;

SELECT SUM(CustomerID) FROM
	(SELECT City, COUNT(Customer.CustomerID) as CustomerID
	FROM SalesLT.Customer
	JOIN SalesLT.CustomerAddress
	ON SalesLT.Customer.CustomerID = SalesLT.CustomerAddress.CustomerID
	JOIN SalesLT.Address
	On SalesLT.CustomerAddress.AddressID = SalesLT.Address.AddressID
	Group By City) as temp;

SELECT City, SalesPerson, COUNT(Customer.CustomerID) 
FROM SalesLT.Customer
JOIN SalesLT.CustomerAddress
ON SalesLT.Customer.CustomerID = SalesLT.CustomerAddress.CustomerID
JOIN SalesLT.Address
On SalesLT.CustomerAddress.AddressID = SalesLT.Address.AddressID
Group By City, SalesPerson;

SELECT SUM(C) FROM 
	(SELECT City, Count(SalesPerson) as SP, SUM(CustomerId) as C FROM
		(SELECT City, SalesPerson, COUNT(Customer.CustomerID) as CustomerID
		FROM SalesLT.Customer
		JOIN SalesLT.CustomerAddress
		ON SalesLT.Customer.CustomerID = SalesLT.CustomerAddress.CustomerID
		JOIN SalesLT.Address
		On SalesLT.CustomerAddress.AddressID = SalesLT.Address.AddressID
		Group By City, SalesPerson) as temp
		Group By City) as temp2
*/


SELECT City, SUM(CustomerId) as Customers, Count(SalesPerson) as Sales FROM
	(SELECT City, SalesPerson, COUNT(Customer.CustomerID) as CustomerID
	FROM SalesLT.Customer
	JOIN SalesLT.CustomerAddress
	ON SalesLT.Customer.CustomerID = SalesLT.CustomerAddress.CustomerID
	JOIN SalesLT.Address
	On SalesLT.CustomerAddress.AddressID = SalesLT.Address.AddressID
	Group By City, SalesPerson) as temp
	Group By City


/* ex4? */

/*
SELECT *
FROM SalesLT.ProductCategory
WHERE ProductCategoryID NOT IN (SELECT ParentProductCategoryID FROM SalesLT.ProductCategory);
GO

SELECT *
FROM SalesLT.ProductCategory
EXCEPT
SELECT *
FROM SalesLT.ProductCategory
WHERE ProductCategoryID IN (SELECT ParentProductCategoryID FROM SalesLT.ProductCategory);


SELECT ProductCategoryID, Name
FROM SalesLT.ProductCategory
WHERE ProductCategoryID IN (SELECT ParentProductCategoryID FROM SalesLT.ProductCategory);

INSERT INTO SalesLT.Product (Name, ProductNumber, StandardCost, ListPrice, ProductCategoryID, SellStartDate) VALUES
('Bike',      21, 220, 240, 1, GETDATE()),
('SparePart', 22, 5, 7,     2, GETDATE()),
('Gear',      23, 1, 2,     2, GETDATE());
*/
SELECT SalesLT.ProductCategory.NAME, SalesLT.Product.NAME  
FROM SalesLT.Product
JOIN SalesLT.ProductCategory 
ON SalesLT.ProductCategory.ProductCategoryID = SalesLT.Product.ProductCategoryID 
WHERE SalesLT.Product.ProductCategoryID IN 
	(SELECT ProductCategoryID
	FROM SalesLT.ProductCategory
	WHERE ProductCategoryID IN (SELECT ParentProductCategoryID FROM SalesLT.ProductCategory));


/* ex5 */

/*
SELECT FirstName, LastName 
From SalesLT.Customer;

SELECT UnitPriceDiscount, SalesOrderID, [SalesOrderDetailID]
FROM SalesLT.SalesOrderDetail
WHERE UnitPriceDiscount > 0;

SELECT DISTINCT FirstName, LastName, UnitPriceDiscount, SalesLT.SalesOrderHeader.SalesOrderID, SalesLT.SalesOrderDetail.SalesOrderDetailID
FROM SalesLT.Customer
JOIN SalesLT.SalesOrderHeader
ON SalesLT.Customer.CustomerID = SalesLT.SalesOrderHeader.CustomerID
JOIN SalesLT.SalesOrderDetail
ON SalesLT.SalesOrderHeader.SalesOrderID = SalesLT.SalesOrderDetail.SalesOrderID
WHERE UnitPriceDiscount > 0;
*/

SELECT FirstName, LastName, SUM(UnitPriceDiscount) AS DiscountSum
FROM SalesLT.Customer
JOIN SalesLT.SalesOrderHeader
ON SalesLT.Customer.CustomerID = SalesLT.SalesOrderHeader.CustomerID
JOIN SalesLT.SalesOrderDetail
ON SalesLT.SalesOrderHeader.SalesOrderID = SalesLT.SalesOrderDetail.SalesOrderID
WHERE UnitPriceDiscount > 0
Group BY Customer.CustomerID, FirstName, LastName

/* ex6 */

ALTER TABLE SalesLT.Customer
ADD CreditCardNumber varchar(20) not null DEFAULT '000000000000000',
CONSTRAINT Customer_CreditCardNumber_Length CHECK (LEN(CreditCardNumber) BETWEEN 15 AND 16);

INSERT INTO SalesLT.Customer([NameStyle], [FirstName], [LastName], [PasswordHash], [PasswordSalt], [ModifiedDate], [CreditCardNumber]) VALUES
(0, 'Marko', 'Golovko', 'rwfv2g2egwdw', 'sccsa', GETDATE(), '1531626');

INSERT INTO SalesLT.Customer([NameStyle], [FirstName], [LastName], [PasswordHash], [PasswordSalt], [ModifiedDate], [CreditCardNumber]) VALUES
(0, 'Marko', 'Golovko', 'rwfv2g2egwdw', 'sccsa', GETDATE(), '583768477748593');

