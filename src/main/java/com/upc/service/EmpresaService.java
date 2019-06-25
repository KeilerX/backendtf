package com.upc.service;

import java.util.List;

import com.upc.model.entity.Empresa;
import com.upc.model.entity.Recomendacion;

public interface EmpresaService 
	extends CrudService<Empresa>{

	Recomendacion recomendar(Recomendacion recomendacion);
	List<Recomendacion> listarRecomendaciones(int id);
}
