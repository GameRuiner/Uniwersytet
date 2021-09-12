DROP TYPE IF EXISTS CzytelnikIdTableType;
GO

CREATE TYPE CzytelnikIdTableType AS TABLE ( id INT)
Go

drop procedure if exists p2
go

create procedure p2 @tableID CzytelnikIdTableType READONLY
as
begin
	SELECT Czytelnik.Czytelnik_ID, sum(liczba_dni) as liczba_dni  FROM Czytelnik
	JOIN Wypozyczenie ON Czytelnik.Czytelnik_ID = Wypozyczenie.Czytelnik_ID
	GROUP BY Czytelnik.Czytelnik_ID;
end
go

declare @CzytelnikIdType AS CzytelnikIdTableType;
INSERT INTO @CzytelnikIdType (id) SELECT czytelnik_id FROM Czytelnik;
exec p2 @tableID = @CzytelnikIdType
go



-- for test

SELECT * FROM Ksiazka;
SELECT * FROM Wypozyczenie;
select PESEL, Czytelnik_ID from Czytelnik
