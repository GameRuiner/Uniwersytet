DROP TABLE IF EXISTS Cache;
GO

DROP TABLE IF EXISTS History;
GO

DROP TABLE IF EXISTS ParametersT;
GO

CREATE TABLE Cache
( ID INT IDENTITY
, UrlAddress VARCHAR(100)
, LastAccess DATETIME
);

CREATE TABLE History
( ID INT IDENTITY
, UrlAddress VARCHAR(100)
, LastAccess DATETIME
);

CREATE TABLE ParametersT
( NameP VARCHAR(100)
, ValueP INT
);

INSERT INTO ParametersT (NameP, ValueP) VALUES
('max_cache', 3);
GO

drop trigger if exists tr_insert_cache
go

create trigger tr_insert_cache on Cache instead of insert
as
begin
  declare @urlAddress VARCHAR(100) = (SELECT UrlAddress FROM inserted)
  declare @max_cache INT =  (SELECT ValueP FROM ParametersT)
  IF (SELECT count(UrlAddress) FROM Cache WHERE UrlAddress = @urlAddress) > 0
	UPDATE Cache SET LastAccess = GETDATE() WHERE UrlAddress = LastAccess
  ELSE
	IF (SELECT COUNT(ID) FROM Cache) < @max_cache
		INSERT INTO Cache (UrlAddress, LastAccess) VALUES (@urlAddress, GETDATE())
	ELSE
	begin
		INSERT INTO History (UrlAddress, LastAccess)
		SELECT TOP 1 UrlAddress, LastAccess FROM Cache
		ORDER BY LastAccess;
		DELETE FROM Cache WHERE ID IN (SELECT TOP 1 ID FROM Cache ORDER BY LastAccess)
		INSERT INTO Cache (UrlAddress, LastAccess) VALUES (@urlAddress, GETDATE())
	end 
end
go

drop trigger if exists tr_insert_history
go

create trigger tr_insert_history on History instead of insert
as
begin
  declare @urlAddress VARCHAR(100) = (SELECT UrlAddress FROM inserted)
  IF (SELECT count(UrlAddress) FROM History WHERE UrlAddress = @urlAddress) > 0
	UPDATE History SET LastAccess = GETDATE() WHERE UrlAddress = @urlAddress
  ELSE
	INSERT INTO History (UrlAddress, LastAccess) VALUES (@urlAddress, GETDATE()) 
end
go

INSERT INTO Cache (UrlAddress) VALUES ('google.com');
INSERT INTO Cache (UrlAddress) VALUES ('google2.com');
INSERT INTO Cache (UrlAddress) VALUES ('google3.com');
INSERT INTO Cache (UrlAddress) VALUES ('google4.com');
INSERT INTO Cache (UrlAddress) VALUES ('google5.com');
--test
declare @d DATE = GETDATE();
print @d

SELECT * FROM Cache;
SELECT count(UrlAddress) FROM Cache;
INSERT INTO Cache (UrlAddress, LastAccess) VALUES ('google.com', GETDATE());
INSERT INTO Cache (UrlAddress, LastAccess) VALUES ('google2.com', GETDATE());

INSERT INTO History (UrlAddress, LastAccess)
SELECT TOP 1 UrlAddress, LastAccess FROM Cache
ORDER BY LastAccess

SELECT * FROM History;