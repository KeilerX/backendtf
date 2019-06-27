package com.upc.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.upc.model.entity.Postulacion;

@Repository
public interface PostulacionRepository 
	extends JpaRepository<Postulacion, Integer>{
	
	@Query("FROM Postulacion p WHERE p.trabajador.id=:trabajadorId")
	List<Postulacion> getPostulacionesPorTrabajador(@Param("trabajadorId") int trabajadorId);
	@Query("FROM Postulacion p WHERE p.trabajo.id=:trabajoId")
	List<Postulacion> getPostulacionesPorTrabajo(@Param("trabajoId") int trabajoId);
	
	@Modifying
	@Query("UPDATE Postulacion p SET p.estado=CASE WHEN (p.id=:postulacionId) THEN 1 ELSE 2 END WHERE p.trabajo.id=:trabajoId")
	void aceptar(@Param("postulacionId") int postulacionId, @Param("trabajoId") int trabajoId);
	
	@Query("FROM Postulacion p WHERE p.trabajo.id=:trabajoId and p.trabajador.id=:trabajadorId")
    Postulacion getMismaPostulacion(@Param("trabajadorId") int trabajadorId,@Param("trabajoId") int trabajoId);
	
}
