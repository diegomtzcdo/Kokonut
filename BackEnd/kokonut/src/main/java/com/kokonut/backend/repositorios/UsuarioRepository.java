package com.kokonut.backend.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kokonut.backend.modelos.Usuario;

import java.util.List;
import java.util.Optional;

@Repository("usuarioRepository")
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
	Optional<Usuario> findByEmail(String email);

    List<Usuario> findByIdIn(List<Long> userIds);

    Optional<Usuario> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
    
    @Procedure("CreateSPUser")
    void createUserSP(@Param("avatar") String avatar, @Param("email") String email, @Param("enabled") Boolean enabled, @Param("pass")String pass, 
    		@Param("user")String user, @Param("code") String code, @Param("name") String name);
    
    @Procedure("CreateSPMod")
    void createModSP(@Param("avatar") String avatar, @Param("email") String email, @Param("enabled") Boolean enabled, @Param("pass")String pass, 
    		@Param("user")String user, @Param("code") String code, @Param("name") String name);
    
    @Procedure("ConfirmEmailSP")
    void confirmEmail(@Param("code") String code);
    
    @Procedure("UpdateUserSP")
    void updateUser(@Param("avatar") String avatar, @Param("email") String email, @Param("pass")String pass, @Param("user")String user, 
    		@Param("name") String name, @Param("oldMail") String oldMail);
    
    @Procedure("DeleteUserSP")
    void deleteUser(@Param("oldMail") String oldMail);
}
