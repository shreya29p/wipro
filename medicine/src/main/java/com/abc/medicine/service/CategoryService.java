package com.abc.medicine.service;

import java.util.List;

import com.abc.medicine.entity.Category;

public interface CategoryService {
	
    Category saveCategory(Category category);
	
	Category getCategoryById(int categoryId);
 
	List<Category> getAllCategory();

}
