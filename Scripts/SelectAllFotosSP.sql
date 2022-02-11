use kokonutPrueba
go
CREATE or ALTER PROCEDURE SelectAllFotoSP (@page INT, @size INT)
AS
BEGIN
	DECLARE @offset INT
	DECLARE @start NVARCHAR(12)
	DECLARE @ending NVARCHAR(12)
	DECLARE @sql NVARCHAR(MAX)
	SET @offset = @page*@size
	SET @start = CONVERT(NVARCHAR(12), @offset);
	SET @ending = CONVERT(NVARCHAR(12), @size);
	SET @sql = 'SELECT id_foto, name, latitude, longitude, enabled FROM kokonutPrueba.dbo.fotos ' +
		'ORDER BY id_foto OFFSET ' + @start + ' ROWS FETCH NEXT ' + @ending + ' ROWS ONLY';
	EXECUTE (@sql)

END 