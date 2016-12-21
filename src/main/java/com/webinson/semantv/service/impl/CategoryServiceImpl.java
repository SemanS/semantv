package com.webinson.semantv.service.impl;

import com.webinson.semantv.assembler.CategoryAssembler;
import com.webinson.semantv.dao.CategoryDao;
import com.webinson.semantv.dto.CategoryDto;
import com.webinson.semantv.entity.Category;
import com.webinson.semantv.service.CategoryService;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Slavo on 12/2/2016.
 */
@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    CategoryDao categoryDao;

    @Autowired
    CategoryAssembler categoryAssembler;

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryAssembler.toDtos(categoryDao.findAll());
    }

    @Override
    public CategoryDto getCategory() {
        return categoryAssembler.toDto(categoryDao.findOne(1L));
    }

    @Override
    public void saveCategory(String categoryDto) {
        Category category = new Category();
        category.setName(categoryDto);
        categoryDao.save(category);
    }

}
