package com.webinson.semantv.service.impl;

import com.webinson.semantv.assembler.ItemAssembler;
import com.webinson.semantv.dao.ItemDao;
import com.webinson.semantv.dto.ItemDto;
import com.webinson.semantv.entity.Item;
import com.webinson.semantv.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Slavo on 10/17/2016.
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemDao itemDao;

    @Autowired
    ItemAssembler itemAssembler;

    public List<ItemDto> getAllItems() {

        List<ItemDto> items = new ArrayList<ItemDto>();
        items = itemAssembler.toDtos(itemDao.findAll());
        return items;

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
    public void saveItemByUrl(String url, ItemDto itemDto) {
        Item item = itemDao.findByUrl(url);
        itemAssembler.convertToModel(item, itemDto);
        itemDao.save(item);
    }

    @Override
    public void saveNewItem(String url, String img) {
        Item item = new Item();
        item.setUrl(url);
        item.setImg(img);
        itemDao.save(item);
    }

    @Override
    public void deleteItem(String url) {
        Item item = new Item();
        item = itemDao.findByUrl(url);
        itemDao.delete(item);
    }

    @Override
    public void saveImgByUrl(String url, String img) {
        Item item = itemDao.findByUrl(url);
        item.setImg(img);
        itemDao.save(item);

    }

}
