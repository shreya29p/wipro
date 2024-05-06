package com.abc.order.model;

import java.time.LocalDate;



public class Medicine {
	
	private int medieId;
	private String mediName;
	private double mediPrice;
    private LocalDate mfd;
	private LocalDate efd;
	private String description;
    private Category category;
	 
	public int getMedieId() {
		return medieId;
	}
	public void setMedieId(int medieId) {
		this.medieId = medieId;
	}
	public String getMediName() {
		return mediName;
	}
	public void setMediName(String mediName) {
		this.mediName = mediName;
	}
	public double getMediPrice() {
		return mediPrice;
	}
	public void setMediPrice(double mediPrice) {
		this.mediPrice = mediPrice;
	}
	public LocalDate getMfd() {
		return mfd;
	}
	public void setMfd(LocalDate mfd) {
		this.mfd = mfd;
	}
	public LocalDate getEfd() {
		return efd;
	}
	public void setEfd(LocalDate efd) {
		this.efd = efd;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	 
}