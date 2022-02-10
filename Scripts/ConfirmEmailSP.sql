use kokonutPrueba
go
CREATE OR ALTER PROCEDURE ConfirmEmailSP (@code VARCHAR(255))
AS
BEGIN


UPDATE kokonutPrueba.dbo.usuarios
   SET [enabled] = 1
      ,[codigo] = NULL
 WHERE [codigo] = @code



END 