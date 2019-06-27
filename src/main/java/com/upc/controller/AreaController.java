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
import com.upc.model.entity.Area;
import com.upc.service.AreaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/areas")
@Api(value = "Servicio REST para las áreas")
public class AreaController {
	@Autowired
	private AreaService areaService;
	
	@ApiOperation("Retorna una lista de areas")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Area>> listar(){
		List<Area> areas = new ArrayList<>();
		areas = areaService.listar();
		return new ResponseEntity<List<Area>>(areas, HttpStatus.OK);
	}
	
	@ApiOperation("Retorna una lista de areas por trabajo")
	@GetMapping(value="/trabajo/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Area>> listarPorTrabajo(@PathVariable("id") Integer id){
		List<Area> areas = new ArrayList<>();
		areas = areaService.listarPorTrabajo(id);
		return new ResponseEntity<List<Area>>(areas, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Area> listarId(@PathVariable("id") Integer id) {
		Optional<Area> area = areaService.listId(id);
		if (!area.isPresent()) {
			throw new ModeloNotFoundException("ID: " + id);
		}
		
		return new ResponseEntity<Area>(area.get(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Area> registrar(@Valid @RequestBody Area area){
		Area areaNew = new Area();
		areaNew = areaService.registrar(area);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(areaNew.getId()).toUri();
		return ResponseEntity.created(location).build();		
	}
	
	@PutMapping
	public ResponseEntity<Area> actualizar(@Valid @RequestBody Area area) {		
		areaService.modificar(area);
		return new ResponseEntity<Area>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void eliminar(@PathVariable Integer id) {
		Optional<Area> area = areaService.listId(id);
		if (!area.isPresent()) {
			throw new ModeloNotFoundException("ID: " + id);
		} else {
			areaService.eliminar(id);
		}
	}
}
