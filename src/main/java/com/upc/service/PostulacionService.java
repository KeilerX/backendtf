package com.upc.service;

import java.util.List;

import com.upc.model.entity.Postulacion;

public interface PostulacionService 
	extends CrudService<Postulacion>{

	List<Postulacion> listarPorTrabajador(int id);
	List<Postulacion> listarPorTrabajo(int id);
	boolean contratar(int empresaId,int postulacionId);
}
