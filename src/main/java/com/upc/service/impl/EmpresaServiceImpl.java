package com.upc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upc.model.entity.Empresa;
import com.upc.model.repository.EmpresaRepository;
import com.upc.service.EmpresaService;

@Service
public class EmpresaServiceImpl implements EmpresaService{
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Override
	public Empresa registrar(Empresa t) {		
		if(empresaRepository.getMismaEmpresa(t.getNombre())==null)
			return empresaRepository.save(t);
		else return null;
	}

	@Override
	public Empresa modificar(Empresa t) {
		return empresaRepository.save(t);
	}

	@Override
	public void eliminar(int id) {
		empresaRepository.deleteById(id);		
	}

	@Override
	public Optional<Empresa> listId(int id) {
		return empresaRepository.findById(id);
	}

	@Override
	public List<Empresa> listar() {
		return empresaRepository.findAll();
	}
}
