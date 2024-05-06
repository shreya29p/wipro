package com.abc.medicine.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "category_tbl")
public class Category {
    
    @Id
    @Column(name="category_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int categoryId;
    
    @Column(name="category_name", length = 20)
    private String categoryName;
    
    @OneToMany(mappedBy = "category")
    @JsonIgnore // Prevent circular reference
    private List<Medicine> medicines;

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<Medicine> getMedicines() {
		return medicines;
	}

	public void setMedicines(List<Medicine> medicines) {
		this.medicines = medicines;
	}
	public Category() {
        // Default constructor is needed for JPA
    }

	public Category(int categoryId, String categoryName, List<Medicine> medicines) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.medicines = medicines;
	}

    
}