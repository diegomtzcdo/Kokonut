package com.kokonut.backend.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javaxt.io.Image;

import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/test")
public class PruebaController {
	
	@PostMapping("/subir")
	public Boolean subirFoto(@RequestParam("foto") MultipartFile foto) {
		
		if(!foto.isEmpty()) {
			String archivo = "test-" + foto.getOriginalFilename();
			Path ruta = Paths.get("fotos").resolve(archivo).toAbsolutePath();
			
			try {
				
				Files.copy(foto.getInputStream(), ruta);
				Image img = new Image(foto.getInputStream());
				double[] cord = img.getGPSCoordinate();
				Double Lat = null, Log = null;
				if(cord!= null) {
					Log = cord[0];
					Lat = cord[1];
				}
			} catch(IOException e) {
				
			}
		}
		
		return true;
		
	}
	
	public void prueba(String s) {
		
	}
	
	public void prueba2(String s) {
		/*try (Metadata metadata = new Metadata("eiffel-tower.jpg")) {
			IExif root = (IExif) metadata.getRootPackage();
			if (root.getExifPackage() != null) {
				// Extract EXIF Package
				ExifPackage exifPackage = root.getExifPackage();
				System.out.println("Make : " + exifPackage.getMake());
				System.out.println("Model : " + exifPackage.getModel());
				System.out.println("Width : " + exifPackage.getImageWidth());
				System.out.println("Length : " + exifPackage.getImageLength());
				System.out.println("DateTime : " + exifPackage.getDateTime());					
			} 
		}*/
	}

}
