package com.kokonut.backend.controller;

import java.net.MalformedURLException;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kokonut.backend.modelos.Foto;
import com.kokonut.backend.servicios.FotoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/fotos")
@CrossOrigin(origins="*")
public class FotoController {
	
	@Autowired
	private FotoService fotoService;
	
	@GetMapping("/all/{page}")
	@Secured("ROLE_MOD")
	@ApiOperation(value = "Todas las fotos registradas", notes = "regresa todas las fotos registradas, se necesita el rol de mod"  )
	public List<Foto> getAll(@PathVariable("page") Integer page) {
		return fotoService.getAll(page);
	}
	
	@GetMapping("/byEmail/{page}")
	@Secured("ROLE_USER")
	@ResponseBody
	@ApiOperation(value = "Fotos asignadas al Usuario", notes = "Regresa solo las fotos asociadas al usuario registrado, solo la puede usar un usuario normmal" )
	public List<Foto> getFotosByUser(@PathVariable("page") Integer page, Principal principal) {
		return fotoService.getFotoUser(page, principal.getName());
	}
	
	@PostMapping("/subir")
	@Secured("ROLE_USER")
	@ResponseBody
	@ApiOperation(value = "Permite cargar una foto y la asocia al usuario", notes = "Solo puede usarla un usuario normal" )
	public Foto subirFoto(@RequestParam("foto") MultipartFile foto, Principal principal) {
		return fotoService.subirFoto(foto, principal.getName());
		
	}
	
	@DeleteMapping("/borrar/{id}")
	@Secured("ROLE_USER")
	@ResponseBody
	@ApiOperation(value = "Borra la foto con el id seleccionado", notes = "Solo se puede usar con un usuario normal" )
	public String deleteFotoUser(@PathVariable("id") Long id, Principal principal) {
		return fotoService.borrarFotoById(id, principal.getName());
	}
	
	@DeleteMapping("/borrarMod/{id}")
	@Secured("ROLE_MOD")
	@ResponseBody
	@ApiOperation(value = "Borra cualquier foto deacuerdo al id enviado", notes = "Se necesita el rol de moderador" )
	public String deleteFotoMod(@PathVariable("id") Long id) {
		return fotoService.borrarFotoByIdMod(id);
	}
	
	@GetMapping("/uploads/img/{nombreFoto:.+}")
	@ApiOperation(value = "Foto a partir de su nombre", notes = "Regresa la foto segun el nombre con el que se creo acceso publico" )
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){
		Resource recurso = null;
		try {
			recurso = fotoService.cargar(nombreFoto);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new ResourceNotFoundException("Algo salio mal al cargar el nombr de la foto");
		}
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}

}
