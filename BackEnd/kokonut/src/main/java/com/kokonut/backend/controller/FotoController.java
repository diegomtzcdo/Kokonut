package com.kokonut.backend.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/fotos")
@CrossOrigin(origins="*")
public class FotoController {
	
	@Autowired
	private FotoService fotoService;
	
	@GetMapping("/all/{page}")
	@Secured("ROLE_MOD")
	public List<Foto> getAll(@PathVariable("page") Integer page) {
		return fotoService.getAll(page);
	}
	
	@GetMapping("/byEmail/{page}")
	@Secured("ROLE_USER")
	@ResponseBody
	public List<Foto> getFotosByUser(@PathVariable("page") Integer page, Principal principal) {
		return fotoService.getFotoUser(page, principal.getName());
	}
	
	@PostMapping("/subir")
	@Secured("ROLE_USER")
	@ResponseBody
	public Foto subirFoto(@RequestParam("foto") MultipartFile foto, Principal principal) {
		return fotoService.subirFoto(foto, principal.getName());
		
	}
	
	@DeleteMapping("/borrar/{id}")
	@Secured("ROLE_USER")
	@ResponseBody
	public String deleteFotoUser(@PathVariable("id") Long id, Principal principal) {
		return fotoService.borrarFotoById(id, principal.getName());
	}
	
	@DeleteMapping("/borrarMod/{id}")
	@Secured("ROLE_MOD")
	@ResponseBody
	public String deleteFotoMod(@PathVariable("id") Long id) {
		return fotoService.borrarFotoByIdMod(id);
	}

}
