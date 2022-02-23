package com.run2biz.api.rest;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.run2biz.api.model.Persona;
import com.run2biz.api.service.PersonaService;

@RestController
@RequestMapping ("/api/persona/")
public class PersonaREST {
	
	@Autowired
	private PersonaService personaService;
	
	//CONFIGURAR ROUTE PARA GUARDAR DATOS DE UNA NUEVA PERSONA
	
	@CrossOrigin(origins = "https://indicedemassacorporea.herokuapp.com")
	@PostMapping
	private ResponseEntity<Persona> guardar (@RequestBody Persona persona) {
		
		Persona temporal = personaService.create(persona);
		
		try {
			
			return ResponseEntity.created(new URI("/api/persona"+ temporal.getId())).body(temporal);
			
		}catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			
		}
		
	}
	
	//CONFIGURAR ROUTE PARA OBTER LOS DATOS LISTADOS DE LAS PERSONAS
	
	@GetMapping
	private ResponseEntity<List<Persona>> listarPersonas () {
		
		return ResponseEntity.ok(personaService.getAllPersonas());
		
	}
	
	// ROUTE PARA ELIMINAR PERSONA EN ESPECIFICO
	
	@CrossOrigin(origins = "https://indicedemassacorporea.herokuapp.com")
	@DeleteMapping("/{id}")
	private ResponseEntity<Void> eliminarPersona (@PathVariable ("id") Long id) {
		
		Optional<Persona> p = personaService.findById(id);
		 
		personaService.delete(p);
		
		return ResponseEntity.ok().build();
		
	}
	
	
	//CONFIGURAR ROUTE PARA OBTER LOS DATOS LISTADOS DE LAS PERSONAS
	
	@GetMapping(value = "{id}")
	private ResponseEntity<Optional<Persona>> buscarPersona (@PathVariable ("id") Long id) {
		
		return ResponseEntity.ok(personaService.findById(id));
		
	}

}
