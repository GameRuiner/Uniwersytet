DECLARE @EndTime datetime
DECLARE @StartTime datetime 
SELECT @StartTime=GETDATE() 

SELECT DISTINCT c.PESEL, c.Nazwisko
FROM Egzemplarz e
JOIN Ksiazka k ON e.Ksiazka_ID=k.Ksiazka_ID
JOIN Wypozyczenie w ON e.Egzemplarz_ID=w.Egzemplarz_ID
JOIN Czytelnik c ON c.Czytelnik_ID = w.Czytelnik_ID;



SELECT @EndTime=GETDATE()

--This will return execution time of your query
SELECT DATEDIFF(ms,@StartTime,@EndTime) AS [Duration in millisecs] 

GO

DECLARE @EndTime datetime
DECLARE @StartTime datetime 
SELECT @StartTime=GETDATE() 

SELECT c.PESEL, c.Nazwisko
FROM Czytelnik c WHERE c.Czytelnik_ID IN
(SELECT w.Czytelnik_ID FROM Wypozyczenie w
JOIN Egzemplarz e ON e.Egzemplarz_ID=w.Egzemplarz_ID
JOIN Ksiazka k ON e.Ksiazka_ID=k.Ksiazka_ID)

SELECT @EndTime=GETDATE()

--This will return execution time of your query
SELECT DATEDIFF(ms,@StartTime,@EndTime) AS [Duration in millisecs] 

GO

DECLARE @EndTime datetime
DECLARE @StartTime datetime 
SELECT @StartTime=GETDATE() 

SELECT c.PESEL, c.Nazwisko
FROM Czytelnik c WHERE c.Czytelnik_ID IN
(SELECT w.Czytelnik_ID FROM Wypozyczenie w
WHERE w.Egzemplarz_ID IN 
(SELECT Egzemplarz_ID FROM Egzemplarz e 
JOIN Ksiazka k ON e.Ksiazka_ID=k.Ksiazka_ID))

SELECT @EndTime=GETDATE()

--This will return execution time of your query
SELECT DATEDIFF(ms,@StartTime,@EndTime) AS [Duration in millisecs] 

GO


DECLARE @val AS INT = 1007;
DECLARE @val2 AS INT = 1;

WHILE @val <= 2007
    BEGIN
        INSERT Egzemplarz(Ksiazka_ID, Sygnatura)
        SELECT @val, @val2;
        SET @val = @val + 1;
		SET @val2 = @val2 + 1;
    END
GO

DECLARE @val AS INT = 1;
DECLARE @val2 AS INT = 3000;

WHILE @val <= 1000
    BEGIN
        INSERT Wypozyczenie(Czytelnik_ID, Egzemplarz_ID)
        SELECT @val, @val2;
        SET @val = @val + 1;
		SET @val2 = @val2 + 1;
    END

DECLARE @val3 AS INT = 1;

WHILE @val3 <= 1000
    BEGIN
        INSERT Ksiazka(Ksiazka_ID)
        SELECT @val3;
		SET @val3 = @val3 + 1;
    END

SELECT * FROM Czytelnik;
SELECT * FROM Egzemplarz;
SELECT * FROM Ksiazka;
SELECT * FROM Wypozyczenie;

