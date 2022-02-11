USE [kokonutPrueba]
GO
CREATE OR ALTER PROCEDURE CreateFotoSP (@name    VARCHAR(255),
							  @latitude		float,
							  @longitude	float,
                              @enabled      bit,
                              @email        VARCHAR(255))
AS
BEGIN
DECLARE @idFoto int;
INSERT INTO [kokonutPrueba].[dbo].[fotos]
           ([latitude]
           ,[longitude]
           ,[enabled]
           ,[name])
     VALUES
           (@latitude
           ,@longitude
           ,@enabled
           ,@name);
SET @idFoto = (SELECT id_foto From [kokonutPrueba].[dbo].[fotos] Where [name] = @name);
INSERT INTO kokonutPrueba.dbo.usuarios_fotos ([usuario_id_usuario], [fotos_id_foto]) 
	SELECT [id_usuario], @idFoto FROM kokonutPrueba.dbo.usuarios WHERE [email] = @email;

END 