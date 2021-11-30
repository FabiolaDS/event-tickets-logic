package com.eventtickets.logictier.service;

import com.eventtickets.logictier.model.Category;

import java.util.List;

public interface CategoryService {
	Category createCategory(Category category);
	List<Category> getAllCategories();
}
