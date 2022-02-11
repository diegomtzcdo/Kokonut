package com.kokonut.backend.servicios.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kokonut.backend.modelos.Foto;

public interface IFotoService {
	
	public List<Foto> getAll(Integer page);
	
	public List<Foto> getFotoUser(Integer page, String email);
	
	public Foto subirFoto(MultipartFile foto, String email);
	
	public String borrarFotoById(Long id, String email);
	
	public String borrarFotoByIdMod(Long id);

}
