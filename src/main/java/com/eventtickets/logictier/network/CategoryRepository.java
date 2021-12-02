package com.eventtickets.logictier.network;

import com.eventtickets.logictier.model.Category;

import java.util.List;

public interface CategoryRepository {
	Category createCategory(Category category);
	List<Category> getAllCategories();
	Category findByName(String category);
}
