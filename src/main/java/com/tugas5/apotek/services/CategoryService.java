package com.tugas5.apotek.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tugas5.apotek.models.Category;
import com.tugas5.apotek.repositories.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    public boolean canDeleteCategory(Integer id) {
        Category category = getCategoryById(id);
        return category.getObats().isEmpty();
    }

    public boolean deleteCategory(Integer id) {
        if (!canDeleteCategory(id)) {
            return false;
        }
        categoryRepository.deleteById(id);
        return true;
    }

    public Category getCategoryById(Integer id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public Category updateCategory(Integer id, Category category) {
        Category existingCategory = getCategoryById(id);
        existingCategory.setName(category.getName());
        existingCategory.setDescription(category.getDescription());
        return categoryRepository.save(existingCategory);
    }
}


