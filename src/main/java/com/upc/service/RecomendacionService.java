package com.upc.service;

import java.util.List;

import com.upc.model.entity.Recomendacion;

public interface RecomendacionService 
	extends CrudService<Recomendacion>{

	List<Recomendacion> listarPorTrabajador(int id);
	List<Recomendacion> listarPorEmpresa(int id);
}
