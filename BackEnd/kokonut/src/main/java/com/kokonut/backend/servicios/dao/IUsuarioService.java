package com.kokonut.backend.servicios.dao;

import com.kokonut.backend.payload.UsuarioPayload;

public interface IUsuarioService {
	
	public UsuarioPayload create(UsuarioPayload s);
	
	public UsuarioPayload createMod(UsuarioPayload s);
	
	public UsuarioPayload actualizarUsuario(UsuarioPayload newUsr, String OldUsr);
	
	public String borrarUsuario(String email);

}
