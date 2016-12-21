package com.webinson.semantv.service;

import com.webinson.semantv.dto.CategoryDto;
import com.webinson.semantv.entity.Category;
import org.primefaces.model.TreeNode;

import java.util.List;

/**
 * Created by Slavo on 12/2/2016.
 */
public interface CategoryService {
    public List<CategoryDto> getAllCategories();
    public CategoryDto getCategory();
    public void saveCategory(String categoryDto);

}
