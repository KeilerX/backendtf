package com.upc.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upc.model.entity.Trabajador;
import com.upc.model.repository.PostulacionRepository;
import com.upc.model.repository.TrabajadorRepository;
import com.upc.service.TrabajadorService;

@Service
public class TrabajadorServiceImpl implements TrabajadorService{
	@Autowired
	private TrabajadorRepository trabajadorRepository;
	@Autowired
	private PostulacionRepository postulacionRepository;
	
	@Override
	public Trabajador registrar(Trabajador t) {	
		if(trabajadorRepository.getMismoTrabajador(t.getNombres(),t.getApellidos())==null)
			return trabajadorRepository.save(t);
		else return null;
	}

	@Override
	public Trabajador modificar(Trabajador t) {
		return trabajadorRepository.save(t);
	}

	@Override
	public void eliminar(int id) {
		trabajadorRepository.deleteById(id);		
	}

	@Override
	public Optional<Trabajador> listId(int id) {
		return trabajadorRepository.findById(id);
	}

	@Override
	public List<Trabajador> listar() {
		return trabajadorRepository.findAll();
	}
	
	@Override
	public List<Trabajador> listarPorTrabajo(int id){
		List<Trabajador> trabajadores = new ArrayList<>();
		postulacionRepository.getPostulacionesPorTrabajo(id)
			.forEach(postulacion->
			trabajadores.add(postulacion.getTrabajador())
			);
		return trabajadores;
	}
}
