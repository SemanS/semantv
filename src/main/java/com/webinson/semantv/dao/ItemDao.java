package com.webinson.semantv.dao;

import com.webinson.semantv.entity.Category;
import com.webinson.semantv.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Slavo on 10/17/2016.
 */
@Repository
public interface ItemDao extends JpaRepository<Item, Long>, QueryDslPredicateExecutor<Item> {

    Item findByUrl(String url);

    List<Item> findItemsByCategoryName(Category category);

    /*List<Item> findAllItems();*/

}
