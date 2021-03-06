use kokonutPrueba
go
CREATE OR ALTER PROCEDURE CreateSPUser (@avatar    VARCHAR(255) = '',
							  @email     VARCHAR(255),
                              @enabled        bit,
                              @pass          VARCHAR(255),
                              @user VARCHAR(255),
							  @code VARCHAR(255),
							  @name VARCHAR(255))
AS
BEGIN

INSERT INTO kokonutPrueba.dbo.usuarios
           ([avatar]
           ,[email]
           ,[enabled]
           ,[password]
           ,[username]
		   ,[full_name]
		   ,[codigo])
     VALUES
           (@avatar
           ,@email
           ,@enabled
           ,@pass
           ,@user
		   ,@name
		   ,@code);
INSERT INTO kokonutPrueba.dbo.usuarios_roles ([usuario_id_usuario], [roles_id_rol]) 
	SELECT [id_usuario], 1 FROM kokonutPrueba.dbo.usuarios WHERE [email] = @email;

END 