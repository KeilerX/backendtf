package com.upc.service;

import java.util.List;

import com.upc.model.entity.Postulacion;
import com.upc.model.entity.Recomendacion;
import com.upc.model.entity.Trabajador;

public interface TrabajadorService 
	extends CrudService<Trabajador>{

	Postulacion postular(Postulacion postulacion);
	List<Postulacion> listarPostulaciones(int id);
	List<Recomendacion> listarRecomendaciones(int id);
}
