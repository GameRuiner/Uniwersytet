DROP TABLE IF EXISTS Employees;
GO

DROP TABLE IF EXISTS SalaryHistory;
GO

DROP TABLE IF EXISTS Logs;
GO

CREATE TABLE Employees
( ID INT IDENTITY
, SalaryGros FLOAT
);

CREATE TABLE SalaryHistory
( ID INT IDENTITY
, EmployeeID INT
, SalaryYear INT
, SalaryMonth INT
, SalaryNet FLOAT
, SalaryGros FLOAT
);
go

CREATE TABLE Logs
( ID INT IDENTITY
, EmployeeID INT
, LOGMessage VARCHAR (1000)
, SalaryMonth INT
, SalaryYear INT
);
go

INSERT INTO Employees (SalaryGros) VALUES
(20000),
(5000),
(6000),
(7000),
(8000);
GO

INSERT INTO SalaryHistory (EmployeeID, SalaryYear, SalaryMonth, SalaryGros) VALUES
(1, 2021, 1, 20000),
(1, 2021, 2, 20000),
(1, 2020, 1, 20000),
(2, 2021, 1, 4000),
(3, 2021, 1, 4200),
(2, 2021, 2, 4500);
GO


drop procedure if exists p1
go


declare c_salaries cursor for SELECT EmployeeID, Employees.SalaryGros as CurrentSalary, SalaryMonth, SalaryHistory.SalaryGros 
FROM SalaryHistory
JOIN Employees ON SalaryHistory.EmployeeID = Employees.ID
WHERE SalaryYear = YEAR(GETDATE())
ORDER BY EmployeeID, SalaryMonth
go



drop procedure if exists p1
go

create procedure p1 @month int
as
begin
	declare @employeeID INT
	declare @currentSalary FLOAT
	declare @salaryMonth INT
	declare @salaryGros FLOAT
	declare @salarySum FLOAT = 0
	declare @salaryYear FLOAT
	declare @tax FLOAT
	declare @MontnInYear INT = 12
	declare @threshold FLOAT = 85528
	declare @taxSmall FLOAT = 18
	declare @taxBig FLOAT = 32
	declare @lastEmployee INT = -1
	declare @lastMonth INT = -1
	open c_salaries
	fetch next from c_salaries into @employeeID, @currentSalary, @salaryMonth, @salaryGros
	while ( @@FETCH_STATUS=0 )
	begin
		IF @salaryMonth+1 = @month OR (@lastEmployee != @employeeID AND @lastEmployee != -1)
		BEGIN
			set @salarySum = @salarySum + @salaryGros
			set @salaryYear = @salarySum + (12 - @month)*@currentSalary;
			IF @salaryYear > @threshold
				set @tax =  @taxBig
			ELSE
				set @tax =  @taxSmall
			INSERT INTO SalaryHistory (EmployeeID, SalaryYear, SalaryMonth, SalaryNet, SalaryGros) VALUES 
			(@employeeID,  YEAR(GETDATE()), @month, @currentSalary*((100-@tax)/100), @currentSalary);
			set @lastEmployee = -1
			set @salarySum = 0
			set @lastMonth = -1
		END
		ELSE 
		BEGIN
			set @salarySum = @salarySum + @salaryGros
			set @lastEmployee = @employeeID
			set @lastMonth = @salaryMonth
		END
		IF @lastMonth + 1 != @salaryMonth AND @lastMonth != -1
				INSERT INTO Logs (EmployeeID, LOGMessage, SalaryMonth, SalaryYear) VALUES
				(@employeeID, 'No data for current month', @lastMonth + 1, YEAR(GETDATE()))
		fetch next from c_salaries into @employeeID, @currentSalary, @salaryMonth, @salaryGros
	end
	IF @lastEmployee != -1
		INSERT INTO SalaryHistory (EmployeeID, SalaryYear, SalaryMonth, SalaryNet, SalaryGros) VALUES 
			(@employeeID,  YEAR(GETDATE()), @month, @currentSalary*((100-@tax)/100), @currentSalary);
	close c_salaries
end
go

exec p1 @month=3
go

deallocate c_salaries
go



--test
SELECT EmployeeID, Employees.SalaryGros as CurrentSalary, SalaryMonth, SalaryHistory.SalaryGros 
FROM SalaryHistory
JOIN Employees ON SalaryHistory.EmployeeID = Employees.ID
WHERE SalaryYear = YEAR(GETDATE())
ORDER BY EmployeeID, SalaryMonth

SELECT * FROM Employees;
SELECT * FROM SalaryHistory;
SELECT * FROM Logs;