package com.upc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upc.model.entity.Empresa;
import com.upc.model.entity.Postulacion;
import com.upc.model.repository.EmpresaRepository;
import com.upc.model.repository.PostulacionRepository;
import com.upc.model.repository.TrabajoRepository;
import com.upc.service.PostulacionService;

@Service
public class PostulacionServiceImpl implements PostulacionService{

	@Autowired
	private PostulacionRepository postulacionRepository;
	@Autowired
	private EmpresaRepository empresaRepository;
	@Autowired
	private TrabajoRepository trabajoRepository;
	
	@Override
	public Postulacion registrar(Postulacion t) {
		t.setEstado(0);
        if(postulacionRepository.getMismaPostulacion(t.getTrabajador().getId(),
                t.getTrabajo().getId())
                ==null)
            return postulacionRepository.save(t);
        else return null;
	}

	@Override
	public Postulacion modificar(Postulacion t) {
		return postulacionRepository.save(t);
	}

	@Override
	public void eliminar(int id) {
		postulacionRepository.deleteById(id);		
	}

	@Override
	public Optional<Postulacion> listId(int id) {
		return postulacionRepository.findById(id);
	}

	@Override
	public List<Postulacion> listar() {
		return postulacionRepository.findAll();
	}
	
	@Override
	public List<Postulacion> listarPorTrabajador(int id){
		return postulacionRepository.getPostulacionesPorTrabajador(id);
	}
	
	@Override
	public List<Postulacion> listarPorTrabajo(int id){
		return postulacionRepository.getPostulacionesPorTrabajo(id);
	}
	
	@Transactional
	@Override
	public boolean contratar(int empresaId,int postulacionId){
		Optional<Empresa> empresaOptional = empresaRepository.findById(empresaId);
		Optional<Postulacion> postulacionOptional = postulacionRepository.findById(postulacionId);
		int postulacion_trabajo_id=postulacionOptional.get().getTrabajo().getId();
		Empresa postulacion_empresa = postulacionOptional.get().getTrabajo().getEmpresa();
		if(!empresaOptional.isPresent() || !postulacionOptional.isPresent()) {
			return false;
		}
		if(postulacion_empresa.getId() != empresaOptional.get().getId()) {
			return false;
		}
		postulacionRepository.aceptar(postulacionId,postulacion_trabajo_id);
		trabajoRepository.aceptar(postulacion_trabajo_id);
		return true;
	}
}
