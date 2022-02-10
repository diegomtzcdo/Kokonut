package com.kokonut.backend.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UsuarioPayload {
	
	@NotEmpty(message = "*Porfavor ingresa tu nombre completo")
	private String fullName;
	@NotEmpty(message = "*Porfavor ingresa una contraseña")
	private String password;
	@NotEmpty(message = "*Porfavor ingresa un nombre de usuario")
	private String username;
	private String avatar;
	@Email(message = "*Porfavor ingresa un correo Valido")
    @NotEmpty(message = "*Porfavor ingresa un correo")
	private String email;

}
