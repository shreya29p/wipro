package com.abc.medicine.service;

import java.util.List;

import com.abc.medicine.entity.Medicine;


public interface MedicineService {
	Medicine saveMedicine(Medicine medicine);

	Medicine saveMedicine(int categoryId,Medicine medicine);

	Medicine getMedicineById(int medieId);

	List<Medicine> getAllMedicines();

	Medicine updateMedicine(Medicine medicine);

	void deleteMedicine(int medieId);

	List<Medicine> getMedicinesByCategory(String categoryName);

	
}