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
import com.upc.model.entity.Empresa;
import com.upc.service.EmpresaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/empresas")
@Api(value = "Servicio REST para las empresas")
public class EmpresaController {

	@Autowired
	private EmpresaService empresaService;
	
	@ApiOperation("Rertorna una lista de empresas")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Empresa>> listar(){
		List<Empresa> empresas = new ArrayList<>();
		empresas = empresaService.listar();
		return new ResponseEntity<List<Empresa>>(empresas, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Empresa> listarId(@PathVariable("id") Integer id) {
		Optional<Empresa> empresa = empresaService.listId(id);
		if (!empresa.isPresent()) {
			throw new ModeloNotFoundException("ID: " + id);
		}
		
		return new ResponseEntity<Empresa>(empresa.get(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Boolean> registrar(@Valid @RequestBody Empresa empresa){
		Optional<Empresa> empresaOptional = Optional.ofNullable(empresaService.registrar(empresa));
		if(empresaOptional.isPresent()) {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(empresaOptional.get().getId()).toUri();
			return ResponseEntity.created(location).build();
		}
		else {
			return new ResponseEntity<Boolean>(false,HttpStatus.OK);
		}
	}
	
	@PutMapping
	public ResponseEntity<Empresa> actualizar(@Valid @RequestBody Empresa empresa) {		
		empresaService.modificar(empresa);
		return new ResponseEntity<Empresa>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void eliminar(@PathVariable Integer id) {
		Optional<Empresa> empresa = empresaService.listId(id);
		if (!empresa.isPresent()) {
			throw new ModeloNotFoundException("ID: " + id);
		} else {
			empresaService.eliminar(id);
		}
	}
}
