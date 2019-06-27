package com.upc.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.upc.model.entity.Trabajo;

@Repository
public interface TrabajoRepository 
	extends JpaRepository<Trabajo, Integer>{

	@Query("FROM Trabajo t WHERE t.empresa.id=:id")
	List<Trabajo> getTrabajosPorEmpresa(@Param("id") int id);
	@Modifying
	@Query("UPDATE Trabajo t SET t.estado=true WHERE t.id=:trabajoId")
	void aceptar(@Param("trabajoId") int trabajoId);
}
