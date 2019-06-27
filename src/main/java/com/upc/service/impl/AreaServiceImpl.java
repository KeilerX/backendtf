package com.upc.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upc.model.entity.Area;
import com.upc.model.repository.AreaRepository;
import com.upc.model.repository.AreaTrabajoRepository;
import com.upc.service.AreaService;

@Service
public class AreaServiceImpl implements AreaService{

	@Autowired
	private AreaRepository areaRepository;
	@Autowired
	private AreaTrabajoRepository areaTrabajoRepository;
	
	@Override
	public Area registrar(Area t) {		
		return areaRepository.save(t);
	}

	@Override
	public Area modificar(Area t) {
		return areaRepository.save(t);
	}

	@Override
	public void eliminar(int id) {
		areaRepository.deleteById(id);		
	}

	@Override
	public Optional<Area> listId(int id) {
		return areaRepository.findById(id);
	}

	@Override
	public List<Area> listar() {
		return areaRepository.findAll();
	}
	
	@Override
	public List<Area> listarPorTrabajo(int id){
		List<Area> areas = new ArrayList<>();
		areaTrabajoRepository.getAreasPorTrabajo(id)
			.forEach(areaTrabajo->
				areas.add(areaTrabajo.getArea())
			);
		return areas;
	}
}
