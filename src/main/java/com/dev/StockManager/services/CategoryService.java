package com.dev.StockManager.services;

import com.dev.StockManager.entities.Category;
import com.dev.StockManager.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> ListAll (){
        return categoryRepository.findAll();
    }
}
