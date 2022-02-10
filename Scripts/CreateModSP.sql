use kokonutPrueba
go
CREATE OR ALTER PROCEDURE CreateSPMod (@avatar    VARCHAR(255) = '',
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
	SELECT [id_usuario], 2 FROM kokonutPrueba.dbo.usuarios where [email] = @email;

END 