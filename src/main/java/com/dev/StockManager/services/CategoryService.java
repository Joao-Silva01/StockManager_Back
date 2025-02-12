package com.dev.StockManager.services;

import com.dev.StockManager.dtos.CategoryDTO;
import com.dev.StockManager.entities.Category;
import com.dev.StockManager.exceptions.IdNotFoundException;
import com.dev.StockManager.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryDTO> ListAll (){
        List<Category> category = categoryRepository.findAll();
        return category.stream().map(CategoryDTO::new).toList();
    }

    public CategoryDTO findbyCategory(Integer id){
        Category category = categoryRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Category not found!"));
        return new CategoryDTO(category);
    }

    @Transactional
    public void create(CategoryDTO category){
        Category newCategory = new Category(category.getName());
        categoryRepository.save(newCategory);
    }
}
