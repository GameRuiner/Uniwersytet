DROP TABLE IF EXISTS firstnames;
GO

DROP TABLE IF EXISTS lastnames;
GO


CREATE TABLE firstnames
( id INT IDENTITY
, firstname VARCHAR(200)
, CONSTRAINT id_PK PRIMARY KEY (id)
);

CREATE TABLE lastnames
( id INT IDENTITY
, lastname VARCHAR(200)
, CONSTRAINT id_PK2 PRIMARY KEY (id)
);

INSERT INTO firstnames (firstname) VALUES
('Liam'),
('Oliver'),
('William'),
('Olivia'),
('Emma'),
('Ava'),
('Sophia'),
('Noah');
GO

INSERT INTO lastnames (lastname) VALUES
('Smith'),
('Johnson'),
('Williams'),
('Brown'),
('Jones'),
('Garcia'),
('Miller'),
('Davis');
GO

drop procedure if exists p1
go
create procedure p1 @n int = 3
as
begin
	DROP TABLE IF EXISTS fdata;
	CREATE TABLE fdata
	( firstname VARCHAR(200)
	, lastname VARCHAR(200)
	, CONSTRAINT fdata_PK PRIMARY KEY (firstname,lastname)
	);
	INSERT INTO fdata
	SELECT TOP(@n) firstname, lastname FROM firstnames CROSS JOIN lastnames
	ORDER BY NEWID();
	IF @n > (SELECT count(firstname) FROM fdata) THROW 50000, 'n is larger than the number all possible pairs', 1;
end
go
exec p1 @n=11
SELECT * FROM fdata
exec p1 @n=15
SELECT * FROM fdata
exec p1 @n=65
go


-- test
IF (SELECT count(firstname) FROM firstnames) > 6 THROW 50000, 'n is larger than the number all possible pairs', 1;