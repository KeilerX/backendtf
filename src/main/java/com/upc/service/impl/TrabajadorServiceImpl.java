package com.upc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upc.model.entity.Postulacion;
import com.upc.model.entity.Recomendacion;
import com.upc.model.entity.Trabajador;
import com.upc.model.repository.PostulacionRepository;
import com.upc.model.repository.RecomendacionRepository;
import com.upc.model.repository.TrabajadorRepository;
import com.upc.service.TrabajadorService;

@Service
public class TrabajadorServiceImpl implements TrabajadorService{
	@Autowired
	private TrabajadorRepository trabajadorRepository;
	@Autowired
	private PostulacionRepository postulacionRepository;
	@Autowired
	private RecomendacionRepository recomendacionRepository;
	
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
	public List<Postulacion> listarPostulaciones(int id){
		return postulacionRepository.getPostulacionesPorTrabajador(id);
	}
	@Override
	public List<Recomendacion> listarRecomendaciones(int id){
		return recomendacionRepository.getRecomendacionesPorTrabajador(id);
	}
	@Override
	public Postulacion postular(Postulacion postulacion) {
		postulacion.setEstado(1);
		return postulacionRepository.save(postulacion);
	}
}
