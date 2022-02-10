USE [kokonutPrueba]
GO
CREATE PROCEDURE UpdateUserSP (@avatar    VARCHAR(255) = '',
							  @email     VARCHAR(255),
                              @pass          VARCHAR(255),
                              @user VARCHAR(255),
							  @name VARCHAR(255),
							  @oldMail VARCHAR(255))
AS
BEGIN
UPDATE [dbo].[usuarios]
   SET [avatar] = @avatar
      ,[email] = @email
      ,[password] = @pass
      ,[username] = @user
      ,[full_name] = @name
 WHERE [email] = @oldMail;
 END


