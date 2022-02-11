use kokonutPrueba
go
CREATE or ALTER PROCEDURE SelectByUserFotoSP (@page INT, @size INT, @email varchar(255))
AS
BEGIN
	DECLARE @offset INT
	DECLARE @sql NVARCHAR(MAX)
	SET @offset = @page*@size;
	SELECT DISTINCT F.id_foto, F.name, F.latitude, F.longitude, F.enabled FROM kokonutPrueba.dbo.fotos F INNER JOIN kokonutPrueba.dbo.usuarios_fotos UF 
		ON F.id_foto = UF.usuario_id_usuario INNER JOIN kokonutPrueba.dbo.usuarios U ON UF.usuario_id_usuario = U.id_usuario WHERE
		U.email = @email AND F.[enabled] = 1 ORDER BY F.id_foto OFFSET @offset ROWS FETCH NEXT @size ROWS ONLY;

END 