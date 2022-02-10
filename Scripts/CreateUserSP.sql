use kokonutPrueba
go
CREATE OR ALTER PROCEDURE CreateSPUser (@avatar    VARCHAR(255) = '',
							  @email     VARCHAR(255),
                              @enabled        bit,
                              @pass          VARCHAR(255),
                              @user VARCHAR(255),
							  @code VARCHAR(255))
AS
BEGIN


INSERT INTO kokonutPrueba.dbo.usuarios
           ([avatar]
           ,[email]
           ,[enabled]
           ,[password]
           ,[username]
		   ,[codigo])
     VALUES
           (@avatar
           ,@email
           ,@enabled
           ,@pass
           ,@user
		   ,@code)

END 