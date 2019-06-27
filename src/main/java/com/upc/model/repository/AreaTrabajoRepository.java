package com.upc.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.upc.model.entity.AreaTrabajo;

@Repository
public interface AreaTrabajoRepository 
	extends JpaRepository<AreaTrabajo, Integer>{

	@Modifying
	@Query(value="INSERT INTO area_trabajos(trabajo_id,area_id) VALUES(:trabajoId,:areaId) ",nativeQuery=true)
	Integer registrar (@Param("trabajoId") Integer trabajoId,
			@Param("areaId") Integer areaId);
	
	@Query("FROM AreaTrabajo at WHERE at.trabajo.id=:trabajoId")
	List<AreaTrabajo> getAreasPorTrabajo(@Param("trabajoId") Integer trabajoId);
	
	@Query("FROM AreaTrabajo at WHERE at.area.id=:areaId")
	List<AreaTrabajo> getTrabajosPorArea(@Param("areaId") Integer areaId);
}
