package com.upc.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.upc.model.entity.Empresa;

@Repository
public interface EmpresaRepository 
	extends JpaRepository<Empresa, Integer>{

	@Query("FROM Empresa e WHERE e.nombre = :nombre")
	Empresa getMismaEmpresa(@Param("nombre") String nombre);
}
