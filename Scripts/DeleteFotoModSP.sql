USE [kokonutPrueba]
GO
CREATE or ALTER PROCEDURE DeleteFotoMODSP (@idFoto int)
AS
BEGIN
	UPDATE F
	SET F.[enabled] = 0 
	FROM [kokonutPrueba].[dbo].[fotos] F
	WHERE F.[id_foto] = @idFoto;
END


