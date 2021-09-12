DROP TABLE IF EXISTS Products;
GO

DROP TABLE IF EXISTS Prices;
GO

DROP TABLE IF EXISTS Rates;
GO

CREATE TABLE Products
( ID INT IDENTITY
, ProductName VARCHAR(200)
);

CREATE TABLE Prices
( ProductID INT
, Currency VARCHAR(200)
, Price FLOAT
);

CREATE TABLE Rates
( Currency VARCHAR(200)
, PricePLN FLOAT
);
GO

INSERT INTO Products (ProductName) VALUES
('Merchant 2000'),
('Titan 750'),
('Optimal 3P'),
('Chrome'),
('20ara');
GO

INSERT INTO Prices (ProductID, Currency, Price) VALUES
(1, 'PLN', 20),
(2, 'PLN', 10),
(3, 'PLN', 15),
(4, 'PLN', 5),
(5, 'PLN', 8),
(3, 'EUR', 3.22),
(1, 'USD', 5.05),
(2, 'GBP', 1.89);
GO

INSERT INTO Rates (Currency, PricePLN) VALUES
('PLN', 1),
('EUR', 4.65),
('USD', 3.95),
('GBP', 5.46);
GO

declare c_prices cursor for SELECT PricePLN as Rate, P.Price as PLNPrice FROM Prices
LEFT JOIN Rates ON Prices.Currency = Rates.Currency 
JOIN Prices as P ON P.ProductID = Prices.ProductID AND P.Currency = 'PLN' 
declare @rate FLOAT
declare @PLNPrice FLOAT
open c_prices
fetch next from c_prices into @rate, @PLNPrice
while ( @@FETCH_STATUS=0 )
begin
	IF @rate IS NULL
		DELETE from Prices where current of c_prices
	ELSE
		UPDATE Prices SET Price = @PLNPrice / @rate WHERE current of c_prices
	fetch next from c_prices into @rate, @PLNPrice
end 
close c_prices
deallocate c_prices



-- test
SELECT * FROM Products;
SELECT * FROM Prices;
SELECT * FROM Rates;

SELECT Prices.ProductID, Prices.Currency, Prices.Price, PricePLN as Rate, P.Price as PLNPrice FROM Prices
LEFT JOIN Rates ON Prices.Currency = Rates.Currency 
JOIN Prices as P ON P.ProductID = Prices.ProductID AND P.Currency = 'PLN';

UPDATE Rates SET PricePLN = 4.60
WHERE Currency = 'EUR';
go

INSERT INTO Prices (ProductID, Currency, Price) VALUES
(1, 'AUD', 6.62);
GO