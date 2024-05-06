package com.abc.medicine.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.abc.medicine.entity.Medicine;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Integer> {
    List<Medicine> findByCategory_CategoryName(String categoryName);
	
}
