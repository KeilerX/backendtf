package com.upc.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.upc.exception.ModeloNotFoundException;
import com.upc.model.entity.Postulacion;
import com.upc.model.entity.Recomendacion;
import com.upc.model.entity.Trabajador;
import com.upc.service.TrabajadorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/trabajadores")
@Api(value = "Servicio REST para los trabajadores")
public class TrabajadorController {

	@Autowired
	private TrabajadorService trabajadorService;
	
	@ApiOperation("Rertorna una lista de trabajadores")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Trabajador>> listar(){
		List<Trabajador> trabajadores = new ArrayList<>();
		trabajadores = trabajadorService.listar();
		return new ResponseEntity<List<Trabajador>>(trabajadores, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Trabajador> listarId(@PathVariable("id") Integer id) {
		Optional<Trabajador> trabajador = trabajadorService.listId(id);
		if (!trabajador.isPresent()) {
			throw new ModeloNotFoundException("ID: " + id);
		}
		
		return new ResponseEntity<Trabajador>(trabajador.get(), HttpStatus.OK);
	}
	
	@GetMapping(value="/postulaciones",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Postulacion>> listarPostulaciones(@PathVariable("id") Integer id){
		List<Postulacion> postulaciones = new ArrayList<>();
		postulaciones = trabajadorService.listarPostulaciones(id);
		return new ResponseEntity<List<Postulacion>>(postulaciones, HttpStatus.OK);
	}
	
	@GetMapping(value= "/recomendaciones", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Recomendacion>> listarRecomendaciones(@PathVariable("id") Integer id){
		List<Recomendacion> recomendaciones = new ArrayList<>();
		recomendaciones = trabajadorService.listarRecomendaciones(id);
		return new ResponseEntity<List<Recomendacion>>(recomendaciones, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Trabajador> registrar(@Valid @RequestBody Trabajador trabajador){
		Optional<Trabajador> trabajadorOptional = Optional.ofNullable(trabajadorService.registrar(trabajador));
		if(trabajadorOptional.isPresent()) {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(trabajadorOptional.get().getId()).toUri();
			return ResponseEntity.created(location).build();
		}
		else {
			return new ResponseEntity<Trabajador>(HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@PostMapping(value= "/postular")
	public ResponseEntity<Postulacion> postular(@Valid @RequestBody Postulacion postulacion){
		Optional<Postulacion> postulacionOptional = Optional.ofNullable(trabajadorService.postular(postulacion));
		if(postulacionOptional.isPresent()) {
			return new ResponseEntity<Postulacion>(postulacion,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Postulacion>(HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@PutMapping
	public ResponseEntity<Trabajador> actualizar(@Valid @RequestBody Trabajador trabajador) {		
		trabajadorService.modificar(trabajador);
		return new ResponseEntity<Trabajador>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void eliminar(@PathVariable Integer id) {
		Optional<Trabajador> trabajador = trabajadorService.listId(id);
		if (!trabajador.isPresent()) {
			throw new ModeloNotFoundException("ID: " + id);
		} else {
			trabajadorService.eliminar(id);
		}
	}
}
