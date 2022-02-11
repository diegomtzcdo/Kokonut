package com.kokonut.backend.servicios;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.mail.MessagingException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kokonut.backend.exception.AppException;
import com.kokonut.backend.exception.BadRequestException;
import com.kokonut.backend.modelos.Foto;
import com.kokonut.backend.repositorios.FotoRepository;
import com.kokonut.backend.servicios.dao.IFotoService;

import javaxt.io.Image;

@Service
public class FotoService implements IFotoService {

	@Autowired
	private FotoRepository fotoRepository;
	
	@Autowired
	private EmailService emailService;

	@Override
	public List<Foto> getAll(Integer page) {
		Integer size = 10;
		return fotoRepository.selectAllFotos(page, size);
	}

	@Override
	public List<Foto> getFotoUser(Integer page, String email) {
		Integer size = 10;
		return fotoRepository.selectFotosByEmail(page, size, email);
	}

	@Override
	public Foto subirFoto(MultipartFile foto, String email) {
		if (!foto.isEmpty()) {
			String archivo = "F" + UUID.randomUUID().toString().substring(0, 8).toUpperCase() + "-"
					+ foto.getOriginalFilename();
			Path ruta = Paths.get("fotos").resolve(archivo).toAbsolutePath();
			try {
				Files.copy(foto.getInputStream(), ruta);
				Image img = new Image(foto.getInputStream());
				double[] cord = img.getGPSCoordinate();
				Double lat = null, log = null;
				if (cord != null) {
					log = cord[0];
					lat = cord[1];
				}
				Foto fotoObj = new Foto(null, archivo, lat, log, true);
				fotoRepository.createFoto(fotoObj.getName(), fotoObj.getLatitude(), fotoObj.getLatitude(),
						fotoObj.getEnabled(), email);
				return fotoObj;
			} catch (IOException e) {
				throw new AppException("Error al subir la imagen del cliente", e.getCause());
			}
		} else {
			throw new BadRequestException("La foto no se encuentra bien subida");
		}

	}

	@Override
	public String borrarFotoById(Long id, String email) {
		fotoRepository.deleteFotoUser(id, email);
		return "Foto Borrada!";
	}

	@Override
	public String borrarFotoByIdMod(Long id) {
		fotoRepository.deleteFotoMod(id);
		List<Object[]> objFoto = fotoRepository.findInfoById(id);
		if(objFoto.isEmpty()) return "Foto Borrada!";
		String email = objFoto.get(0)[1].toString();
		//String nameFoto = objFoto.get(0)[0].toString();
		Boolean enabledFoto = objFoto.get(0)[2].toString().equals("1");
		if(!enabledFoto) return "Foto Borrada!";
		Map<String, Object> map = new HashMap<String, Object>();
		/*try {
			String base64 = "data:image/png;base64, " + encodeFileToBase64Binary(nameFoto);
			map.put("url", base64);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		try {
			emailService.templateMessageParam(email, "Algo iria aqui", "Foto eliminada por mod", "mailborrado", map);
		} catch (MessagingException e) {
			
			e.printStackTrace();
		}
		return "Foto Borrada!";
	}

	public Resource cargar(String nombreFoto) throws MalformedURLException {
		Path rutaArchivo = getPath(nombreFoto);
		Resource recurso = new UrlResource(rutaArchivo.toUri());
		if (!recurso.exists() && !recurso.isReadable()) {
			throw new ResourceNotFoundException("Foto no encontrada");
		}
		return recurso;
	}
	
	@SuppressWarnings("unused")
	private String encodeFileToBase64Binary(String nombreFoto) throws IOException {
		Path rutaArchivo = getPath(nombreFoto);
	    File file = rutaArchivo.toFile();
	    byte[] fileContent = FileUtils.readFileToByteArray(file);
	    return Base64.getEncoder().encodeToString(fileContent);
	}
	
	private Path getPath(String nombreFoto) {
		return Paths.get("fotos").resolve(nombreFoto).toAbsolutePath();
	}

}
