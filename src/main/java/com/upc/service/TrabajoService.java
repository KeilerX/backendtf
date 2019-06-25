package com.upc.service;

import java.util.List;

import com.upc.dto.TrabajoListaAreaDTO;
import com.upc.model.entity.Postulacion;
import com.upc.model.entity.Trabajo;

public interface TrabajoService 
	extends CrudService<Trabajo>{

	Trabajo registrar(TrabajoListaAreaDTO trabajoDTO);
	List<Trabajo> listarPorEmpresa(int id);
	List<Postulacion> listarPostulaciones(int id);
}
