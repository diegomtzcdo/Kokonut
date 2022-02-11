use kokonutPrueba
go
CREATE or ALTER PROCEDURE SelectInfoFotoSP (@idFoto INT)
AS
BEGIN
	SELECT F.[name], U.[email]
	FROM [kokonutPrueba].[dbo].[fotos] F
	INNER JOIN [kokonutPrueba].[dbo].[usuarios_fotos] UF ON F.id_foto = UF.fotos_id_foto
	INNER JOIN [kokonutPrueba].[dbo].[usuarios] U ON UF.usuario_id_usuario = U.id_usuario
	WHERE F.[id_foto] = @idFoto

END 