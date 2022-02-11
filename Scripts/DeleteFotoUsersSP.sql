USE [kokonutPrueba]
GO
CREATE or ALTER PROCEDURE DeleteFotoUserSP (@idFoto int, @email varchar(255))
AS
BEGIN
	UPDATE F
	SET F.[enabled] = 0 
	FROM [kokonutPrueba].[dbo].[fotos] F
	INNER JOIN [kokonutPrueba].[dbo].[usuarios_fotos] UF ON F.id_foto = UF.fotos_id_foto
	INNER JOIN [kokonutPrueba].[dbo].[usuarios] U ON UF.usuario_id_usuario = U.id_usuario
	WHERE F.[id_foto] = @idFoto AND U.[email] = @email;
END


