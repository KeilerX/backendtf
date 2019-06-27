package com.upc.service;

import java.util.List;

import com.upc.model.entity.Trabajador;

public interface TrabajadorService 
	extends CrudService<Trabajador>{

	List<Trabajador> listarPorTrabajo(int id);
}
