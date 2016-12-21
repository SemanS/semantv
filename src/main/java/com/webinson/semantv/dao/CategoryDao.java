package com.webinson.semantv.dao;

import com.webinson.semantv.dto.CategoryDto;
import com.webinson.semantv.entity.Category;
import com.webinson.semantv.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Slavo on 12/2/2016.
 */
@Repository
public interface CategoryDao extends JpaRepository<Category, Long>, QueryDslPredicateExecutor<Category> {

    Category findByName(String name);

}
