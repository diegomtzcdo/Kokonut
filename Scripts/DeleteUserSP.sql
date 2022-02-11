USE [kokonutPrueba]
GO
CREATE or ALTER PROCEDURE DeleteUserSP (@oldMail VARCHAR(255))
AS
BEGIN
	DECLARE @idUsr int;
	SET @idUsr = ( SELECT id_usuario FROM [kokonutPrueba].[dbo].[usuarios] WHERE [email] = @oldMail);
	DELETE FROM [kokonutPrueba].[dbo].[usuarios_roles] WHERE usuario_id_usuario = @idUsr;
	DELETE FROM [kokonutPrueba].[dbo].[usuarios_fotos] WHERE usuario_id_usuario = @idUsr;
	DELETE FROM [kokonutPrueba].[dbo].[usuarios]
	WHERE [email] = @oldMail;
END


