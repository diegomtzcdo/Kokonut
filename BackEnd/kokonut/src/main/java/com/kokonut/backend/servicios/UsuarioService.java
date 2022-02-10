package com.kokonut.backend.servicios;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kokonut.backend.exception.AppException;
import com.kokonut.backend.modelos.Usuario;
import com.kokonut.backend.repositorios.RolRepository;
import com.kokonut.backend.repositorios.UsuarioRepository;
import com.kokonut.backend.servicios.dao.IUsuarioService;

@Service
public class UsuarioService implements UserDetailsService, IUsuarioService {

	@Autowired
    UsuarioRepository userRepository;
	
	@Autowired
	RolRepository roleRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		// Permite a la gente ingresar con Correo
        Usuario user = userRepository.findByEmail(userEmail)
        		.orElseThrow(() -> 
        			new UsernameNotFoundException("Usuario no encontrado con correo : " + userEmail));
        if(user.getEnabled()) throw new AppException("Usuario no disponible");
        List<GrantedAuthority> authorities = user.getRoles().stream()
        		.map(role -> new SimpleGrantedAuthority(role.getName()))
        		.collect(Collectors.toList());
		//return new User(user.getUsername(), user.getPassword(), enable, accountNonExpired, credencialNonExpired, accountNonLocked, authorities);
		return new User(user.getUsername(), user.getPassword(), user.getEnabled(), true, true, true, authorities);
	}

	/*@Override
	@Transactional(value="seguridadTransactionManager", readOnly = true)
	public Usuario findByUsername(String username) {
		return userRepository.findByUsername(username).orElse(null);
	}

	@Override
	@Transactional(value="seguridadTransactionManager", readOnly = true)
	public Usuario findById(Long id) {
		return userRepository.findById(id)
			.orElseThrow(() -> 
                new UsernameNotFoundException("Usuario no encontrado con e : " + id)
		);
	}

	@Override
	@Transactional(value="seguridadTransactionManager", readOnly = false)
	public Usuario save(Usuario user) {
		return userRepository.save(user);
	}

	@Override
	@Transactional(value="seguridadTransactionManager", readOnly = true)
	public List<Usuario> getAll() {
		return userRepository.findAll();
	}

	@Transactional(value="seguridadTransactionManager", readOnly = false)
	public Usuario create(@Valid Usario user) {
		if(userRepository.existsByUsername(user.getUsername())) {
            return null;
        }

        if(userRepository.existsByEmail(user.getEmail())) {
            return null;
        }

        // Creando cuenta de Usuario
        
        com.polygon.seguridad.model.User u = new com.polygon.seguridad.model.User(user.getName(),
        		user.getUsername(), user.getEmail(), user.getPassword(), user.getEnabled(),
        		user.getFoto(), user.getLastName());
        u.setCreatedAt(Instant.now());
        u.setUpdatedAt(Instant.now());
        user = u;

        //user.setPassword(passwordEncoder.encode(user.getPassword()));

        if(user.getPerfil_id() != null) {
        	user.setPerfil_id(perfilRepository.findById(user.getPerfil_id().getId()).orElse(null));
        }
        if(user.getPerfil_id() == null) {
	        Perfil p = perfilRepository.findByName("Default")
	        		.orElseThrow(() -> new AppException("Perfil de Usuario no establecido."));
	        user.setPerfil_id(p);
        }
        

        return userRepository.save(user);

	}

	public Usuario update(com.polygon.seguridad.model.User user) {
		//user.setPassword(passwordEncoder.encode(user.getPassword()));
		com.polygon.seguridad.model.User u = userRepository.findById(user.getId()).orElseThrow(()->
				new ResourceNotFoundException("usuario", "id", user.getId()));
		u.setName(user.getName());
		u.setUsername(user.getUsername());
		u.setEmail(user.getEmail());
		u.setEnabled(user.getEnabled());
		u.setLastName(user.getLastName());
		u.setUpdatedAt(Instant.now());
		u.setPerfil_id(perfilRepository.findById(user.getPerfil_id().getId()).orElse(null));
		userRepository.save(u);
		System.out.println("aqui");
		return u;
	}

	public boolean delete(Long id) {
		try {
			userRepository.deleteById(id);
			return true;
		} catch(Exception e) {
			return false;
		}
	}*/
	
}
