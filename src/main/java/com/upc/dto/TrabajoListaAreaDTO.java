package com.upc.dto;

import java.util.List;

import com.upc.model.entity.Area;
import com.upc.model.entity.Trabajo;

public class TrabajoListaAreaDTO {

	private Trabajo trabajo;
	private List<Area> lstArea;
	
	public Trabajo getTrabajo() {
		return trabajo;
	}
	public void setTrabajo(Trabajo trabajo) {
		this.trabajo = trabajo;
	}
	public List<Area> getLstArea() {
		return lstArea;
	}
	public void setLstArea(List<Area> lstArea) {
		this.lstArea = lstArea;
	}

	
}
