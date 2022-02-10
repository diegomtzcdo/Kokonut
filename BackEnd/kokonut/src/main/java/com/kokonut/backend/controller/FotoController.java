package com.kokonut.backend.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.http.RequestEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/fotos")
@CrossOrigin(origins="*")
public class FotoController {
	
	@PostMapping("/subir")
	public RequestEntity<?> subirFoto(@RequestParam("foto") MultipartFile foto) {
		
		if(!foto.isEmpty()) {
			String archivo = foto.getName();
			Path ruta = Paths.get("fotos").resolve(archivo).toAbsolutePath();
			
			try {
				Files.copy(foto.getInputStream(), ruta);
			} catch(IOException e) {
				
			}
		}
		
		return null;
		
	}

}
