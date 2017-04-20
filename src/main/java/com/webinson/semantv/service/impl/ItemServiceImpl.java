package com.webinson.semantv.service.impl;

import com.querydsl.jpa.impl.JPAQuery;
import com.webinson.semantv.assembler.CategoryAssembler;
import com.webinson.semantv.assembler.ItemAssembler;
import com.webinson.semantv.dao.CategoryDao;
import com.webinson.semantv.dao.ItemDao;
import com.webinson.semantv.dto.CategoryDto;
import com.webinson.semantv.dto.ItemDto;
import com.webinson.semantv.entity.Category;
import com.webinson.semantv.entity.Item;
import com.webinson.semantv.entity.QItem;
import com.webinson.semantv.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Slavo on 10/17/2016.
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemDao itemDao;

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    ItemAssembler itemAssembler;

    @Autowired
    CategoryAssembler categoryAssembler;

    @PersistenceContext
    EntityManager entityManager;

    public List<ItemDto> getAllItems() {

        final JPAQuery<Item> query = new JPAQuery<>(entityManager);
        QItem item = QItem.item;
        List<Item> items = query.from(item).select(item).orderBy(item.timeStamp.desc()).fetch();
        return itemAssembler.toDtos(items);

    }

    @Override
    public String getTextOfItemByUrl(String url) {
        return itemDao.findByUrl(url).getText();
    }

    @Override
    public String getImgOfItemByUrl(String url) {
        return itemDao.findByUrl(url).getImg();
    }

    @Override
    public ItemDto getItemByUrl(String url) {
        ItemDto itemDto = new ItemDto();
        itemDto = itemAssembler.toDto(itemDao.findByUrl(url));
        return itemDto;
    }

    @Override
    public Item getEntityByUrl(String url) {
        Item item = new Item();
        item = itemDao.findByUrl(url);
        return item;
    }

    @Override
    public void saveItemByUrl(String selectedCategory, Item item) {
        //Item item = itemDao.findByUrl(url);
        //itemAssembler.convertToModel(item, itemDto);
        item.setCategory(categoryDao.findByName(selectedCategory));
        itemDao.save(item);
    }

    @Override
    public void saveNewItem(String url, ItemDto itemDto) {
        Item item = new Item();
        itemAssembler.convertToModel(item, itemDto);
        item.setUrl(url);
        itemDao.save(item);
    }

    @Override
    public void deleteItem(String url) {
        Item item = new Item();
        item = itemDao.findByUrl(url);
        itemDao.delete(item);
    }

    @Override
    public List<ItemDto> getItemsByCategory(CategoryDto categoryDto) {
        Category category = new Category();

        final JPAQuery<Item> query = new JPAQuery<>(entityManager);
        QItem item = QItem.item;
        List<Item> items = query.from(item).select(item).where(item.category.name.eq(categoryDto.getName())).fetch();
        return itemAssembler.toDtos(items);

    }

    @Override
    public void saveTextByUrl(String url, String text) {
        Item item = itemDao.findByUrl(url);
        item.setText(text);
        itemDao.save(item);
    }

    @Override
    public void saveImgByUrl(String url, String img) {
        Item item = itemDao.findByUrl(url);
        item.setImg(img);
        itemDao.save(item);
    }

}
