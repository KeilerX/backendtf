package com.upc.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="area_trabajos",uniqueConstraints={@UniqueConstraint(columnNames={"area_id","trabajo_id"})})
public class AreaTrabajo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "area_id", nullable = false)
	private Area area;
	
	
	@ManyToOne
	@JoinColumn(name = "trabajo_id", nullable = false)
	private Trabajo trabajo;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Area getArea() {
		return area;
	}


	public void setArea(Area area) {
		this.area = area;
	}


	public Trabajo getTrabajo() {
		return trabajo;
	}


	public void setTrabajo(Trabajo trabajo) {
		this.trabajo = trabajo;
	}
	
	
	
	
}
