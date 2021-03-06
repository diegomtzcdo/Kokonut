package com.kokonut.backend.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kokonut.backend.payload.UsuarioPayload;
import com.kokonut.backend.servicios.UsuarioService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private UsuarioService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/registrarse")
	@ApiOperation(value = "Usuario Registrado con Rol de usuario normal", notes = "Crea un usuario Normal" )
	public UsuarioPayload registerUser(@Valid @RequestBody UsuarioPayload user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userService.create(user);
	}
	
	@PostMapping("/registrarMod")
	@ApiOperation(value = "Usuario Registrado con Rol de mod", notes = "Crea un usuario mod" )
	public UsuarioPayload registerMod(@Valid @RequestBody UsuarioPayload user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userService.createMod(user);
	}
	
	@GetMapping(value = "/confirm", produces = MediaType.TEXT_HTML_VALUE)
	@ResponseBody
	@ApiOperation(value = "confirma el email para habilitar el usuario", notes = "Habilida el Usuario" )
	public String confirmEmail(@RequestParam String code) {
		userService.confirmEmail(code);
		return "<html>\n" + "<header><title>Correo Confirmado</title></header>\n" +
		          "<body>\n <div style='width: 100%; text-align: center;'>" + "El correo ha sido confirmado\n" + "</div></body>\n" + "</html>";
	}
	
	@PutMapping("/update")
	@ResponseBody
	@ApiOperation(value = "Usuario Actualizado", notes = "Actualiza el usuario con el correo de loggeo")
	public UsuarioPayload actualizarUsuario(@Valid @RequestBody UsuarioPayload newUser, Principal principal) {
		newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
		return userService.actualizarUsuario(newUser, principal.getName());
	}
	
	@DeleteMapping("/delete")
	@ResponseBody
	@ApiOperation(value = "Borra usuario", notes = "Borra el usuario autenticado" )
	public String borrarUsuario(Principal principal) {
		return userService.borrarUsuario(principal.getName());
	}
	

}
