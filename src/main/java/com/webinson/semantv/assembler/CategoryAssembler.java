package com.webinson.semantv.assembler;

import com.webinson.semantv.dto.CategoryDto;
import com.webinson.semantv.dto.ItemDto;
import com.webinson.semantv.entity.Category;
import com.webinson.semantv.entity.Item;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Slavo on 12/3/2016.
 */
@Component
public class CategoryAssembler {

    public CategoryDto convertToDto(Category model, CategoryDto dto) {
        dto.setId(model.getId());
        dto.setName(model.getName());
        return dto;
    }

    public CategoryDto toDto(Category model) {
        CategoryDto dto = new CategoryDto();
        dto.setId(model.getId());
        dto.setName(model.getName());
        return dto;
    }

    public Category convertToModel(Category category, CategoryDto categoryDto) {
        category.setName(categoryDto.getName());
        return category;
    }

    public Category toModel(CategoryDto categoryDto) {
        Category model = new Category();
        model.setId(categoryDto.getId());
        model.setName(categoryDto.getName());
        return model;
    }

    public List<CategoryDto> toDtos(final Collection<Category> models) {
        final List<CategoryDto> dtos = new ArrayList<>();
        if (isNotEmpty(models)) {
            for (final Category category : models) {
                dtos.add(convertToDto(category, new CategoryDto()));
            }
        }
        return dtos;
    }

    public boolean isNotEmpty(final Collection<?> col) {
        return !isEmpty(col);
    }

    public boolean isEmpty(final Collection<?> col) {
        return col == null || col.isEmpty();
    }


}
