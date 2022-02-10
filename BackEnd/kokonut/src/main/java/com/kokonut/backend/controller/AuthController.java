package com.kokonut.backend.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kokonut.backend.modelos.Usuario;
import com.kokonut.backend.servicios.UsuarioService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private UsuarioService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/registrarse")
	public Usuario registerUser(@Valid @RequestBody Usuario user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userService.create(user);
	}
	
	@GetMapping(value = "/confirm", produces = MediaType.TEXT_HTML_VALUE)
	public String confirmEmail(@RequestParam String code) {
		userService.confirmEmail(code);
		return "<html>\n" + "<header><title>Correo Confirmado</title></header>\n" +
		          "<body>\n <div style='width: 100%; text-align: center;'>" + "El correo ha sido confirmado\n" + "</div></body>\n" + "</html>";
	}

}
