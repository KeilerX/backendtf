package com.upc.service;

import java.util.List;

import com.upc.model.entity.Area;

public interface AreaService 
	extends CrudService<Area>{

	List<Area> listarPorTrabajo(int id);
}
