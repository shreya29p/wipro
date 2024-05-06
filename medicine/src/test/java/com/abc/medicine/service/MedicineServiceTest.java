package com.abc.medicine.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import com.abc.medicine.entity.Category;
import com.abc.medicine.entity.Medicine;
import com.abc.medicine.exception.ResourceNotFoundException;
import com.abc.medicine.repository.CategoryRepository;
import com.abc.medicine.repository.MedicineRepository;



@SpringBootTest(properties="eureka.client.enabled=false")
public class MedicineServiceTest {
	
	@InjectMocks
	private MedicineServiceImpl medicineService;
	
	@Mock
	MedicineRepository medicineRepository;
	
	@Mock
	CategoryRepository categoryRepository;
	
	
	@Test
	void testSaveMedicine() {
	    
		Medicine medicine = new Medicine();
		Category category = new Category();
		medicine.setMedieId(2);
		medicine.setMediName("LXP");
		medicine.setMediPrice(90);
		medicine.setMfd(LocalDate.of(2024, 01, 01));
		medicine.setEfd(LocalDate.of(2027, 05, 01));
		medicine.setDescription("Use for cough");
		category.setCategoryId(1);
		category.setCategoryName("Cough");

	    
	    when(medicineRepository.save(medicine)).thenReturn(medicine);

	    
	    Medicine savedMedicine = medicineService.saveMedicine(medicine);

	   
	    assertEquals("NewMedicine", savedMedicine.getMediName());
	    assertEquals(50, savedMedicine.getMediPrice());
	   
	}

	@Test
	void testSaveMedicineWithException() {
		// Create a new medicine object
		Medicine medicine = new Medicine();
		Category category = new Category();
		medicine.setMedieId(2);
		medicine.setMediName("LXP");
		medicine.setMediPrice(90);
		medicine.setMfd(LocalDate.of(2024, 01, 01));
		medicine.setEfd(LocalDate.of(2027, 05, 01));
		medicine.setDescription("Use for cough");
		category.setCategoryId(1);
		category.setCategoryName("Cough");

	    // Mock the behavior of the medicine repository to throw DataIntegrityViolationException when save is called
	    when(medicineRepository.save(medicine)).thenThrow(DataIntegrityViolationException.class);

	    // Verify that trying to save a medicine with existing unique constraints results in a DataIntegrityViolationException
	    assertThrows(DataIntegrityViolationException.class, () -> medicineService.saveMedicine(medicine));
	}

	
	@Test
	public void testGetMedicineById() {
		
		Medicine medicine = new Medicine();
		Category category = new Category();
		medicine.setMedieId(2);
		medicine.setMediName("LXP");
		medicine.setMediPrice(90);
		medicine.setMfd(LocalDate.of(2024, 01, 01));
		medicine.setEfd(LocalDate.of(2027, 05, 01));
		medicine.setDescription("Use for cough");
		category.setCategoryId(1);
		category.setCategoryName("Cough");
		
		when(medicineRepository.findById(1)).thenReturn(Optional.of(medicine));
		
		Medicine actualObj = medicineService.getMedicineById(1);
		
		assertEquals("LXP",actualObj.getMediName());
	}
	
	@Test
	public void testGetMedicineByIdException() {
		
		when(medicineRepository.findById(2)).thenThrow(new ResourceNotFoundException("Resource not existing with id: 100"));
		assertThrows(ResourceNotFoundException.class, ()->medicineService.getMedicineById(1));
	}
	
	@Test
	public void testGetMedicineDetails() {
		
		Medicine medicine = new Medicine();
		Category category = new Category();
		medicine.setMedieId(2);
		medicine.setMediName("LXP");
		medicine.setMediPrice(90);
		medicine.setMfd(LocalDate.of(2024, 01, 01));
		medicine.setEfd(LocalDate.of(2027, 05, 01));
		medicine.setDescription("Use for cough");
		category.setCategoryId(1);
		category.setCategoryName("Cough");
		
		when(medicineRepository.findById(1)).thenReturn(Optional.of(medicine));
		
		Medicine actualObj = medicineService.getMedicineById(1);
		
		assertEquals("LXP",actualObj.getMediName());
	}
	
	@Test
	public void testGetMedicineDetailsException() {
		
		when(medicineRepository.findById(2)).thenThrow(new ResourceNotFoundException("Resource not existing with id: 2"));
		assertThrows(ResourceNotFoundException.class, ()->medicineService.getMedicineById(1));
	}
	
	@Test
	public void testGetAllMedicines() {
		
		Medicine medicine = new Medicine();
		Category category = new Category();
		medicine.setMedieId(2);
		medicine.setMediName("LXP");
		medicine.setMediPrice(90);
		medicine.setMfd(LocalDate.of(2024, 01, 01));
		medicine.setEfd(LocalDate.of(2027, 05, 01));
		medicine.setDescription("Use for cough");
		category.setCategoryId(2);
		category.setCategoryName("Cough");
		
		
		Medicine medicine1 = new Medicine();
		Category category1 = new Category();
		medicine1.setMedieId(3);
		medicine1.setMediName("DLSYM");
		medicine1.setMediPrice(90);
		medicine1.setMfd(LocalDate.of(2023, 01, 01));
		medicine1.setEfd(LocalDate.of(2027, 05, 31));
		medicine1.setDescription("Use for cough");
		category1.setCategoryId(2);
		category1.setCategoryName("Cough");
		
		
		Medicine medicine2 = new Medicine();
		Category category2 = new Category();
		medicine2.setMedieId(4);
		medicine2.setMediName("Cipla");
		medicine2.setMediPrice(90);
		medicine2.setMfd(LocalDate.of(2024, 11, 21));
		medicine2.setEfd(LocalDate.of(2027, 05, 31));
		medicine2.setDescription("Use for cold");
		category2.setCategoryId(4);
		category2.setCategoryName("Cold");
		
		
		Medicine medicine3 = new Medicine();
		Category category3 = new Category();
		medicine3.setMedieId(5);
		medicine3.setMediName("Dolo");
		medicine3.setMediPrice(35);
		medicine3.setMfd(LocalDate.of(2024, 8, 9));
		medicine3.setEfd(LocalDate.of(2027, 11, 27));
		medicine3.setDescription("Use for fever");
		category3.setCategoryId(5);
		category3.setCategoryName("Fever");
		
		
		List<Medicine> medicines = new ArrayList<>();
		List<Category> categorys = new ArrayList<>();
		medicines.add(medicine);
		categorys.add(category);
		medicines.add(medicine1);
		categorys.add(category1);
		medicines.add(medicine2);
		categorys.add(category2);
		medicines.add(medicine3);
		categorys.add(category3);
		
		when(medicineRepository.findAll()).thenReturn(medicines);
		
		List<Medicine> medicineList = medicineService.getAllMedicines();
		assertEquals(4,medicineList.size());
		}
	
	
	
	@Test
	void testUpdateMedicine() {
	    // Create a medicine object with updated information
	    Medicine updatedMedicine = new Medicine();
	    updatedMedicine.setMedieId(5);
	    updatedMedicine.setMediName("NewName");
	    updatedMedicine.setMediPrice(50);
	    updatedMedicine.setMfd(LocalDate.of(2025, 1, 1));
	    updatedMedicine.setEfd(LocalDate.of(2027, 12, 31));
	    updatedMedicine.setDescription("Updated description");

	    // Mock the behavior of the medicine repository to return the updated medicine when findById is called
	    when(medicineRepository.findById(5)).thenReturn(Optional.of(updatedMedicine));
	    
	    // Call the updateMedicine method
	    Medicine result = medicineService.updateMedicine(updatedMedicine);

	    // Verify that the result matches the updated medicine
	    assertEquals("NewName", result.getMediName());
	    assertEquals(50, result.getMediPrice());
	    // Add more assertions as needed
	}

	@Test
	void testUpdateMedicineWithException() {
	    // Create a medicine object with updated information
	    Medicine updatedMedicine = new Medicine();
	    updatedMedicine.setMedieId(5);
	    updatedMedicine.setMediName("NewName");
	    updatedMedicine.setMediPrice(50);
	    updatedMedicine.setMfd(LocalDate.of(2025, 1, 1));
	    updatedMedicine.setEfd(LocalDate.of(2027, 12, 31));
	    updatedMedicine.setDescription("Updated description");

	    // Mock the behavior of the medicine repository to throw ResourceNotFoundException when findById is called
	    when(medicineRepository.findById(5)).thenThrow(new ResourceNotFoundException("Resource not existing with id: 5"));
	    
	    // Verify that trying to update the medicine results in a ResourceNotFoundException
	    assertThrows(ResourceNotFoundException.class, () -> medicineService.updateMedicine(updatedMedicine));
	}
	
	@Test
	void testDeleteMedicine() {
		
		Medicine medicine3 = new Medicine();
		Category category3 = new Category();
		medicine3.setMedieId(5);
		medicine3.setMediName("Dolo");
		medicine3.setMediPrice(35);
		medicine3.setMfd(LocalDate.of(2024, 8, 9));
		medicine3.setEfd(LocalDate.of(2027, 11, 27));
		medicine3.setDescription("Use for fever");
		category3.setCategoryId(5);
		category3.setCategoryName("Fever");
		
		
		
		when(medicineRepository.findById(5)).thenReturn(Optional.of(medicine3));
		
		doNothing().when(medicineRepository).delete(medicine3);
		
		medicineService.deleteMedicine(5);
		
		verify(medicineRepository,times(1)).findById(5);
		verify(medicineRepository,times(1)).delete(medicine3);	
	}
	
	@Test
	void testDeleteMedicineWithException() {
		
		Medicine medicine3 = new Medicine();
		Category category3 = new Category();
		medicine3.setMedieId(5);
		medicine3.setMediName("Dolo");
		medicine3.setMediPrice(35);
		medicine3.setMfd(LocalDate.of(2024, 8, 9));
		medicine3.setEfd(LocalDate.of(2027, 11, 27));
		medicine3.setDescription("Use for fever");
		category3.setCategoryId(5);
		category3.setCategoryName("Fever");
		
		when(medicineRepository.findById(5)).thenThrow(new ResourceNotFoundException("Resource not existing with id: 5"));
		
		assertThrows(ResourceNotFoundException.class, ()->medicineService.deleteMedicine(5));
		
		verify(medicineRepository,times(0)).delete(medicine3);	
	}



}



















