package com.upc.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upc.dto.TrabajoListaAreaDTO;
import com.upc.model.entity.Postulacion;
import com.upc.model.entity.Trabajo;
import com.upc.model.repository.AreaTrabajoRepository;
import com.upc.model.repository.PostulacionRepository;
import com.upc.model.repository.TrabajoRepository;
import com.upc.service.TrabajoService;

@Service
public class TrabajoServiceImpl implements TrabajoService {

	@Autowired
	private TrabajoRepository trabajoRepository;
	@Autowired
	private AreaTrabajoRepository areaTrabajoRepository;
	@Autowired
	private PostulacionRepository postulacionRepository;
	
	@Override
	public Trabajo registrar(Trabajo t) {
		
		return trabajoRepository.save(t);
	}

	@Override
	public Trabajo modificar(Trabajo t) {
		return trabajoRepository.save(t);
	}

	@Override
	public void eliminar(int id) {
		trabajoRepository.deleteById(id);		
	}

	@Override
	public Optional<Trabajo> listId(int id) {
		return trabajoRepository.findById(id);
	}

	@Override
	public List<Trabajo> listar() {
		return trabajoRepository.findAll();
	}
	
	@Override
	public List<Trabajo> listarPorEmpresa(int id){
		return trabajoRepository.getTrabajosPorEmpresa(id);
	}
	@Override
	public List<Postulacion> listarPostulaciones(int id){
		return postulacionRepository.getPostulacionesPorTrabajo(id);
	}
	
	@Override
	public List<Trabajo> listarPorArea(int id){
		List<Trabajo> trabajos = new ArrayList<>();
		areaTrabajoRepository.getTrabajosPorArea(id)
			.forEach(areaTrabajo->
				trabajos.add(areaTrabajo.getTrabajo())
			);
		return trabajos;
	}
	
	@Override
	public List<Trabajo> listarPorTrabajador(int id){
		List<Trabajo> trabajos = new ArrayList<>();
		postulacionRepository.getPostulacionesPorTrabajador(id)
			.forEach(postulacion->
				trabajos.add(postulacion.getTrabajo())
			);
		return trabajos;
	}
	@Override
	public List<Trabajo> listarDisponibles(){
		List<Trabajo> trabajos = trabajoRepository.findAll();
		trabajos.removeIf(t -> t.getEstado() != false);
		return trabajos;
	}
	
	
	
	@Transactional
	@Override
	public Trabajo registrar(TrabajoListaAreaDTO trabajoDTO) {
		trabajoDTO.getTrabajo().setEstado(false);
		Optional<Trabajo> trabajoOptional = Optional.ofNullable(trabajoRepository.save(trabajoDTO.getTrabajo()));
		trabajoDTO.getLstArea()
					.forEach(area->
					areaTrabajoRepository.registrar(
							trabajoOptional.get().getId(),
								area.getId())
					);
		
		return trabajoDTO.getTrabajo();
	}
}
