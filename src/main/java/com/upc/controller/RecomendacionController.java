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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.upc.exception.ModeloNotFoundException;
import com.upc.model.entity.Recomendacion;
import com.upc.service.RecomendacionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/recomendaciones")
@Api(value = "Servicio REST para las recomendaciones")
public class RecomendacionController {

	@Autowired
	private RecomendacionService recomendacionService;
	
	@ApiOperation("Rertorna una lista de recomendaciones")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Recomendacion>> listar(){
		List<Recomendacion> recomendaciones = new ArrayList<>();
		recomendaciones = recomendacionService.listar();
		return new ResponseEntity<List<Recomendacion>>(recomendaciones, HttpStatus.OK);
	}
	
	@ApiOperation("Rertorna una lista de recomendaciones por trabajador")
	@GetMapping(value= "/trabajador/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Recomendacion>> listarPorTrabajador(@PathVariable("id") Integer id){
		List<Recomendacion> recomendaciones = new ArrayList<>();
		recomendaciones = recomendacionService.listarPorTrabajador(id);
		return new ResponseEntity<List<Recomendacion>>(recomendaciones, HttpStatus.OK);
	}
	
	@ApiOperation("Rertorna una lista de recomendaciones por empresa")
	@GetMapping(value= "/empresa/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Recomendacion>> listarPorEmpresa(@PathVariable("id") Integer id){
		List<Recomendacion> recomendaciones = new ArrayList<>();
		recomendaciones = recomendacionService.listarPorEmpresa(id);
		return new ResponseEntity<List<Recomendacion>>(recomendaciones, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Recomendacion> listarId(@PathVariable("id") Integer id) {
		Optional<Recomendacion> recomendacion = recomendacionService.listId(id);
		if (!recomendacion.isPresent()) {
			throw new ModeloNotFoundException("ID: " + id);
		}
		
		return new ResponseEntity<Recomendacion>(recomendacion.get(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Recomendacion> registrar(@Valid @RequestBody Recomendacion recomendacion){
		Optional<Recomendacion> recomendacionOptional = Optional.ofNullable(recomendacionService.registrar(recomendacion));
		if(recomendacionOptional.isPresent()) {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(recomendacionOptional.get().getId()).toUri();
			return ResponseEntity.created(location).build();
		}
		else {
			return new ResponseEntity<Recomendacion>(HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@PutMapping
	public ResponseEntity<Recomendacion> actualizar(@Valid @RequestBody Recomendacion recomendacion) {		
		recomendacionService.modificar(recomendacion);
		return new ResponseEntity<Recomendacion>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void eliminar(@PathVariable Integer id) {
		Optional<Recomendacion> recomendacion = recomendacionService.listId(id);
		if (!recomendacion.isPresent()) {
			throw new ModeloNotFoundException("ID: " + id);
		} else {
			recomendacionService.eliminar(id);
		}
	}
}
