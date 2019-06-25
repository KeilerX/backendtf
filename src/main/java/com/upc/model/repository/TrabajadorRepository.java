package com.upc.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.upc.model.entity.Trabajador;

@Repository
public interface TrabajadorRepository 
	extends JpaRepository<Trabajador, Integer>{

	@Query(value="SELECT t FROM trabajadores t WHERE t.nombres=:nombres AND t.apellidos=:apellidos", nativeQuery=true)
	Trabajador getMismoTrabajador(@Param("nombres")String nombres,@Param("apellidos") String apellidos);
}
