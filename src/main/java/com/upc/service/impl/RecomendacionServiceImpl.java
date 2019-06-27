package com.upc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upc.model.entity.Recomendacion;
import com.upc.model.repository.RecomendacionRepository;
import com.upc.service.RecomendacionService;

@Service
public class RecomendacionServiceImpl implements RecomendacionService{
	
	@Autowired
	private RecomendacionRepository recomendacionRepository;
	
	@Override
	public Recomendacion registrar(Recomendacion t) {	
		return recomendacionRepository.save(t);
	}

	@Override
	public Recomendacion modificar(Recomendacion t) {
		return recomendacionRepository.save(t);
	}

	@Override
	public void eliminar(int id) {
		recomendacionRepository.deleteById(id);		
	}

	@Override
	public Optional<Recomendacion> listId(int id) {
		return recomendacionRepository.findById(id);
	}

	@Override
	public List<Recomendacion> listar() {
		return recomendacionRepository.findAll();
	}
	
	
	@Override
	public List<Recomendacion> listarPorTrabajador(int id){
		return recomendacionRepository.getRecomendacionesPorTrabajador(id);
	}
	
	@Override
	public List<Recomendacion> listarPorEmpresa(int id){
		return recomendacionRepository.getRecomendacionesPorEmpresa(id);
	}
}
