USE [kokonutPrueba]
GO
CREATE or ALTER PROCEDURE DeleteUserSP (@oldMail VARCHAR(255))
AS
BEGIN
	DELETE FROM [kokonutPrueba].[dbo].[usuarios]
	WHERE [email] = @oldMail;
END


