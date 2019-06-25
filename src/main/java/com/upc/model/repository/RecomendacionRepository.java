package com.upc.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.upc.model.entity.Recomendacion;

@Repository
public interface RecomendacionRepository 
	extends JpaRepository<Recomendacion, Integer>{

	@Query(value="SELECT p FROM Recomendacion p WHERE p.trabajador_id=:id", nativeQuery = true)
	List<Recomendacion> getRecomendacionesPorTrabajador(@Param("id") int id);
	
	@Query(value="SELECT p FROM Recomendacion p WHERE p.empresa_id=:id", nativeQuery = true)
	List<Recomendacion> getRecomendacionesPorEmpresa(@Param("id") int id);
}
