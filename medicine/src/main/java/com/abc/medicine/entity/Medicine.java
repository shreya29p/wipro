package com.abc.medicine.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Past;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "medicine_tbl")
public class Medicine {
    
    @Id
    @Column(name="medi_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int medieId;
    
    @Column(name="medi_name", length = 20)
    private String mediName;
    
    @Column(name="medi_price")
    private double mediPrice;
    
    @Column(name="medi_mfd")
    @Past(message = "Manufacture date must be in the past")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate mfd;
    
    @Column(name="medi_efd")
    @Future(message = "Expiration date must be in the future")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate efd;
    
    @Column(name="medi_des", length = 20)
    private String description;
        
    @ManyToOne
    @JoinColumn(name = "category_id")
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
	
	 public Medicine() {
	        // Default constructor is needed for JPA
	    }


	public Medicine(int medieId, String mediName, double mediPrice, LocalDate mfd, LocalDate efd, String description,
			Category category) {
		super();
		this.medieId = medieId;
		this.mediName = mediName;
		this.mediPrice = mediPrice;
		this.mfd = mfd;
		this.efd = efd;
		this.description = description;
		this.category = category;
	}

	


}