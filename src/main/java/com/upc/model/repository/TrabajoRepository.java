package com.upc.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.upc.model.entity.Trabajo;

@Repository
public interface TrabajoRepository 
	extends JpaRepository<Trabajo, Integer>{

	@Query(value="SELECT t FROM trabajos t WHERE t.empresa_id=:id",nativeQuery=true)
	List<Trabajo> getTrabajosPorEmpresa(@Param("id") int id);
}
