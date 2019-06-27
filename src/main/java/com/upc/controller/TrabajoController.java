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

import com.upc.dto.TrabajoListaAreaDTO;
import com.upc.exception.ModeloNotFoundException;
import com.upc.model.entity.Postulacion;
import com.upc.model.entity.Trabajo;
import com.upc.service.TrabajoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/trabajos")
@Api(value = "Servicio REST para los trabajos")
public class TrabajoController {

	@Autowired
	private TrabajoService trabajoService;
	
	@ApiOperation("Rertorna una lista de trabajos")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Trabajo>> listar(){
		List<Trabajo> trabajos = new ArrayList<>();
		trabajos = trabajoService.listar();
		return new ResponseEntity<List<Trabajo>>(trabajos, HttpStatus.OK);
	}
	
	@ApiOperation("Rertorna una lista de trabajos disponibles")
	@GetMapping(value = "/disponibles",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Trabajo>> listarDisponibles(){
		List<Trabajo> trabajos = new ArrayList<>();
		trabajos = trabajoService.listarDisponibles();
		return new ResponseEntity<List<Trabajo>>(trabajos, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Trabajo> listarId(@PathVariable("id") Integer id) {
		Optional<Trabajo> trabajo = trabajoService.listId(id);
		if (!trabajo.isPresent()) {
			throw new ModeloNotFoundException("ID: " + id);
		}
		
		return new ResponseEntity<Trabajo>(trabajo.get(), HttpStatus.OK);
	}
	
	@ApiOperation("Rertorna una lista de trabajos por Empresa")
	@GetMapping(value= "/empresa/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Trabajo>> listarPorEmpresa(@PathVariable("id") Integer id){
		List<Trabajo> trabajos = new ArrayList<>();
		trabajos = trabajoService.listarPorEmpresa(id);
		return new ResponseEntity<List<Trabajo>>(trabajos, HttpStatus.OK);
	}
	
	@ApiOperation("Rertorna una lista de trabajos por Trabajador")
	@GetMapping(value= "/trabajador/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Trabajo>> listarPorTrabajador(@PathVariable("id") Integer id){
		List<Trabajo> trabajos = new ArrayList<>();
		trabajos = trabajoService.listarPorTrabajador(id);
		return new ResponseEntity<List<Trabajo>>(trabajos, HttpStatus.OK);
	}
	
	@ApiOperation("Rertorna una lista de trabajos por Area")
	@GetMapping(value= "/area/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Trabajo>> listarPorArea(@PathVariable("id") Integer id){
		List<Trabajo> trabajos = new ArrayList<>();
		trabajos = trabajoService.listarPorArea(id);
		return new ResponseEntity<List<Trabajo>>(trabajos, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Trabajo> registrar(@Valid @RequestBody TrabajoListaAreaDTO trabajoDTO){
		Trabajo trabajo = new Trabajo();
		trabajo = trabajoService.registrar(trabajoDTO);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(trabajo.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Trabajo> actualizar(@Valid @RequestBody Trabajo trabajo) {		
		trabajoService.modificar(trabajo);
		return new ResponseEntity<Trabajo>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void eliminar(@PathVariable Integer id) {
		Optional<Trabajo> trabajo = trabajoService.listId(id);
		if (!trabajo.isPresent()) {
			throw new ModeloNotFoundException("ID: " + id);
		} else {
			trabajoService.eliminar(id);
		}
	}
}

