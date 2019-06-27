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
import com.upc.model.entity.Postulacion;
import com.upc.service.PostulacionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/postulaciones")
@Api(value = "Servicio REST para las postulaciones")
public class PostulacionController {

	@Autowired
	private PostulacionService postulacionService;
	
	@ApiOperation("Rertorna una lista de postulaciones por trabajador")
	@GetMapping(value="/trabajador/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Postulacion>> listarPorTrabajador(@PathVariable("id") Integer id){
		List<Postulacion> postulaciones = new ArrayList<>();
		postulaciones = postulacionService.listarPorTrabajador(id);
		return new ResponseEntity<List<Postulacion>>(postulaciones, HttpStatus.OK);
	}
	
	@ApiOperation("Rertorna una lista de postulaciones por trabajo")
	@GetMapping(value="/trabajo/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Postulacion>> listarPorTrabajo(@PathVariable("id") Integer id){
		List<Postulacion> postulaciones = new ArrayList<>();
		postulaciones = postulacionService.listarPorTrabajo(id);
		return new ResponseEntity<List<Postulacion>>(postulaciones, HttpStatus.OK);
	}
	
	@ApiOperation("Rertorna una lista de postulaciones")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Postulacion>> listar(){
		List<Postulacion> postulaciones = new ArrayList<>();
		postulaciones = postulacionService.listar();
		return new ResponseEntity<List<Postulacion>>(postulaciones, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Postulacion> listarId(@PathVariable("id") Integer id) {
		Optional<Postulacion> postulacion = postulacionService.listId(id);
		if (!postulacion.isPresent()) {
			throw new ModeloNotFoundException("ID: " + id);
		}
		
		return new ResponseEntity<Postulacion>(postulacion.get(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Boolean> registrar(@Valid @RequestBody Postulacion postulacion){
		Optional<Postulacion> postulacionOptional = Optional.ofNullable(postulacionService.registrar(postulacion));
		if(postulacionOptional.isPresent()) {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(postulacionOptional.get().getId()).toUri();
			return ResponseEntity.created(location).build();
		}
		else {
			return new ResponseEntity<Boolean>(false,HttpStatus.OK);
		}
	}
	
	@GetMapping(value="/contratar/{id1}/{id2}")
	public ResponseEntity<Boolean> contratar(@PathVariable("id1") Integer empresaId,@PathVariable("id2") Integer postulacionId){
		if(postulacionService.contratar(empresaId,postulacionId))
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		else
			return new ResponseEntity<Boolean>(false,HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<Postulacion> actualizar(@Valid @RequestBody Postulacion postulacion) {		
		postulacionService.modificar(postulacion);
		return new ResponseEntity<Postulacion>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void eliminar(@PathVariable Integer id) {
		Optional<Postulacion> postulacion = postulacionService.listId(id);
		if (!postulacion.isPresent()) {
			throw new ModeloNotFoundException("ID: " + id);
		} else {
			postulacionService.eliminar(id);
		}
	}
}
