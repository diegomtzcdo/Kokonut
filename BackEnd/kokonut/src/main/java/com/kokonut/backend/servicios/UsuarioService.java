package com.kokonut.backend.servicios;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kokonut.backend.exception.AppException;
import com.kokonut.backend.modelos.Usuario;
import com.kokonut.backend.payload.UsuarioPayload;
import com.kokonut.backend.repositorios.RolRepository;
import com.kokonut.backend.repositorios.UsuarioRepository;
import com.kokonut.backend.servicios.dao.IUsuarioService;

@Service
public class UsuarioService implements UserDetailsService, IUsuarioService {

	@Autowired
    private UsuarioRepository userRepository;
	
	@Autowired
	private RolRepository roleRepository;
	
	@Autowired
	private EmailService emailService;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		// Permite ingresar con Correo
        Usuario user = userRepository.findByEmail(userEmail)
        		.orElseThrow(() -> 
        			new UsernameNotFoundException("Usuario no encontrado con correo : " + userEmail));
        if(!user.getEnabled()) throw new AppException("Usuario no disponible, favor de confirmar el correo");
        List<GrantedAuthority> authorities = user.getRoles().stream()
        		.map(role -> new SimpleGrantedAuthority(role.getName()))
        		.collect(Collectors.toList());
		//return new User(user.getUsername(), user.getPassword(), enable, accountNonExpired, credencialNonExpired, accountNonLocked, authorities);
		return new User(user.getEmail(), user.getPassword(), user.getEnabled(), true, true, true, authorities);
	}

	@Override
	public UsuarioPayload create(UsuarioPayload user) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = UUID.randomUUID().toString().substring(0, 16).replace('-', 't');
		userRepository.createUserSP(user.getAvatar(), user.getEmail(), false, user.getPassword(), user.getUsername(), code, user.getFullName());
		map.put("url", "http://localhost:8080/kokonut/v1/api/auth/confirm?code=" + code);
		try {
			emailService.templateMessageParam(user.getEmail(), "Algo iria aqui", "Correo de Confirmación", "mailconfirm", map);
		} catch (MessagingException e) {
			
			e.printStackTrace();
		}
		return user;
	}

	public void confirmEmail(String code) {
		userRepository.confirmEmail(code);
	}

	@Override
	public UsuarioPayload createMod(@Valid UsuarioPayload user) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = UUID.randomUUID().toString().substring(0, 16).replace('-', 't');
		userRepository.createModSP(user.getAvatar(), user.getEmail(), false, user.getPassword(), user.getUsername(), code, user.getFullName());
		map.put("url", "http://localhost:8080/kokonut/v1/api/auth/confirm?code=" + code);
		try {
			emailService.templateMessageParam(user.getEmail(), "Algo iria aqui", "Correo de Confirmación", "mailconfirm", map);
		} catch (MessagingException e) {
			
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public UsuarioPayload actualizarUsuario(UsuarioPayload newUsr, String OldUsr) {
		userRepository.updateUser(newUsr.getAvatar(), newUsr.getEmail(), newUsr.getPassword(), newUsr.getUsername(), newUsr.getFullName(), OldUsr);
		return newUsr;
	}

	@Override
	public String borrarUsuario(String email) {
		userRepository.deleteUser(email);
		return "Usuario Borrado Exitosamente!";
	}
	
}
