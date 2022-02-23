package com.run2biz.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.run2biz.api.model.Persona;
import com.run2biz.api.repository.PersonaRepository;

@Service
public class PersonaService {
	
	@Autowired
	private PersonaRepository personaRepository;
	
	//Metodo para crear persona

	public Persona create (Persona persona) {
		
		return personaRepository.save(persona);
		
	}
	
	//Metodo para listar personas
	
	public List<Persona> getAllPersonas () {
		
		return personaRepository.findAll();
	}
	
	//Eliminar una persona de la lista
	
	public void delete (Optional<Persona> persona) {
		
			personaRepository.deleteById(persona.get().getId());
	}
	
	//Buscar una persona por ID
	
	public Optional<Persona> findById(Long id) {
		
		return personaRepository.findById(id);
	}
}
