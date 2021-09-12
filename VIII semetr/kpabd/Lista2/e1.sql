drop function if exists fn_1
go

create function fn_1(@days int) returns @tablica table(PESEL char(11),  specimens_number int)
begin
  declare @d1 Date, @d2 Date
  set @d1 = CAST( GETDATE() AS Date )
  insert into @tablica select PESEL, countS from Wypozyczenie
  JOIN Czytelnik ON Wypozyczenie.Czytelnik_ID = Czytelnik.Czytelnik_ID
  JOIN (SELECT Wypozyczenie.Czytelnik_ID, COUNT(Wypozyczenie_ID) as countS FROM Wypozyczenie 
                     JOIN Czytelnik ON Wypozyczenie.Czytelnik_ID = Czytelnik.Czytelnik_ID
                     WHERE datediff(day, Wypozyczenie.Data, CAST( GETDATE() AS Date )) < Wypozyczenie.Liczba_Dni
                     Group BY Wypozyczenie.Czytelnik_ID) AS specimens_number 
	    ON specimens_number.Czytelnik_ID = Czytelnik.Czytelnik_ID
  WHERE datediff(day, Wypozyczenie.Data, @d1 ) <= Wypozyczenie.Liczba_Dni AND
        datediff(day, Wypozyczenie.Data, @d1 ) > @days
  return
end
go

select * from fn_1(3)


-- for test

SELECT * FROM Ksiazka;
SELECT * FROM Wypozyczenie;
select PESEL, Czytelnik_ID from Czytelnik
declare @d1 Date, @d2 Date
set @d1 = CAST( GETDATE() AS Date )
set @d2 = DATEADD(day, -2, @d1)
print @d1
select datediff(day, @d1, @d2)
go

SET IDENTITY_INSERT Wypozyczenie ON
INSERT INTO Wypozyczenie (Wypozyczenie_ID,Czytelnik_ID,Egzemplarz_ID,Data,Liczba_Dni) VALUES
(14,1,11,'2021-03-20', 10),
(15,3,12,'2021-03-14', 20);
SET IDENTITY_INSERT Wypozyczenie OFF
GO

SELECT PESEL, datediff(day, Wypozyczenie.Data, CAST( GETDATE() AS Date )) FROM Wypozyczenie 
JOIN Czytelnik ON Wypozyczenie.Czytelnik_ID = Czytelnik.Czytelnik_ID
WHERE Liczba_Dni > 5 ;
go

SELECT Wypozyczenie.Czytelnik_ID, COUNT(Wypozyczenie_ID) FROM Wypozyczenie 
JOIN Czytelnik ON Wypozyczenie.Czytelnik_ID = Czytelnik.Czytelnik_ID
WHERE datediff(day, Wypozyczenie.Data, CAST( GETDATE() AS Date )) < Wypozyczenie.Liczba_Dni
Group BY Wypozyczenie.Czytelnik_ID
go

