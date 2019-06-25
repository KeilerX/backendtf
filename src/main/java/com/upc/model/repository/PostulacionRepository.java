package com.upc.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.upc.model.entity.Postulacion;

@Repository
public interface PostulacionRepository 
	extends JpaRepository<Postulacion, Integer>{

	@Query(value="SELECT p FROM Postulacion p WHERE p.trabajador_id=:id", nativeQuery = true)
	List<Postulacion> getPostulacionesPorTrabajador(@Param("id") int id);
	@Query(value="SELECT p FROM Postulacion p WHERE p.empresa_id=:id", nativeQuery = true)
	List<Postulacion> getPostulacionesPorTrabajo(@Param("id") int id);
}
