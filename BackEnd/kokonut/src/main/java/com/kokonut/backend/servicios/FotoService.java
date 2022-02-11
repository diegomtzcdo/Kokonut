package com.kokonut.backend.servicios;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kokonut.backend.exception.AppException;
import com.kokonut.backend.exception.BadRequestException;
import com.kokonut.backend.modelos.Foto;
import com.kokonut.backend.repositorios.FotoRepository;

import javaxt.io.Image;

@Service
public class FotoService {
	
	@Autowired
	private FotoRepository fotoRepository;
	
	public List<Foto> getAll(Integer page) {
		Integer size = 10;
		return fotoRepository.selectAllFotos(page, size);
	}
	
	public List<Foto> getFotoUser(Integer page, String email) {
		Integer size = 10;
		return fotoRepository.selectFotosByEmail(page, size, email);
	}

	public Foto subirFoto(MultipartFile foto, String email) {
		if(!foto.isEmpty()) {
			String archivo = "F" + UUID.randomUUID().toString().substring(0, 8).toUpperCase() + "-" + foto.getOriginalFilename();
			Path ruta = Paths.get("fotos").resolve(archivo).toAbsolutePath();
			try {
				Files.copy(foto.getInputStream(), ruta);
				Image img = new Image(foto.getInputStream());
				double[] cord = img.getGPSCoordinate();
				Double lat = null, log = null;
				if(cord!= null) {
					log = cord[0];
					lat = cord[1];
				}
				Foto fotoObj = new Foto(null, archivo, lat, log, true);
				fotoRepository.createFoto(fotoObj.getName(), fotoObj.getLatitude(), fotoObj.getLatitude(), fotoObj.getEnabled(), email);
				return fotoObj;
			} catch(IOException e) {
				throw new AppException("Error al subir la imagen del cliente", e.getCause());
			}
		} else {
			throw new BadRequestException("La foto no se encuentra bien subida");
		}
		
	}

}
