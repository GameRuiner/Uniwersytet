drop procedure if exists p3
go

create procedure p3( 
   @title           varchar(75) = NULL,
   @author          varchar(75) = NULL,
   @publicationYear varchar(75) = NULL
)
as
begin
	DECLARE @sqlCommand			   nvarchar(1000)
	DECLARE @TitleFilter           VARCHAR(1000)
	DECLARE @AuthorFilter          VARCHAR(1000)
	DECLARE @PublicationYearFilter VARCHAR(1000)
	DECLARE @all				   VARCHAR(2)   = '-1'

	SET @TitleFilter = CASE WHEN @title IS NULL OR @title = ''
	THEN '''' + @all + ''' = ''' + @all + '''' 
	ELSE 'Tytul like ''%' + @title + '%''' 
	END

	SET @AuthorFilter = CASE WHEN @author IS NULL OR @author = ''
	THEN '''' + @all + ''' = ''' + @all + '''' 
	ELSE 'Autor like ''%' + @author + '%''' 
	END

    SET @PublicationYearFilter = CASE WHEN @publicationYear IS NULL OR @publicationYear = 0 
	THEN '''' + @all + ''' = ''' + @all + '''' 
	ELSE 'Rok_Wydania = ''' +  @publicationYear + '''' 
	END

	SET @sqlCommand = 'SELECT COUNT(Ksiazka_ID) AS specimens FROM Ksiazka
					   WHERE ' + @TitleFilter 
					   + ' AND ' + @AuthorFilter
					   + ' AND ' + @PublicationYearFilter
	EXEC(@sqlCommand)
end
go

exec p3 @author = 'Robert'
exec p3 @title = 'SQL'
exec p3 @title = 'SQL', @author = 'Robert'
exec p3 @title = 'SQL', @publicationYear = 2007
go


-- for test

SELECT * FROM Ksiazka;
SELECT * FROM Wypozyczenie;
select PESEL, Czytelnik_ID from Czytelnik
