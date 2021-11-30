package com.eventtickets.logictier.service;

import com.eventtickets.logictier.model.Category;
import com.eventtickets.logictier.network.CategoryRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

	@NonNull
	private CategoryRepository categoryRepository;
	@NonNull
	private Validator validator;

	@Override
	public Category createCategory(Category category) {
		Set<ConstraintViolation<Category>> violations = validator
			.validate(category);
		for (ConstraintViolation<Category> violation : violations) {
			throw new IllegalArgumentException(violation.getMessage());
		}
		return categoryRepository.createCategory(category);
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryRepository.getAllCategories();
	}
}
