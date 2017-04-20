package com.webinson.semantv.service;

import com.webinson.semantv.dto.CategoryDto;
import com.webinson.semantv.dto.ItemDto;
import com.webinson.semantv.entity.Item;

import java.util.List;

/**
 * Created by Slavo on 10/17/2016.
 */
public interface ItemService {

    public List<ItemDto> getAllItems();

    public String getTextOfItemByUrl(String url);

    public String getImgOfItemByUrl(String url);

    public ItemDto getItemByUrl(String url);

    public Item getEntityByUrl(String url);

    public void saveImgByUrl(String url, String img);

    public void saveItemByUrl(String selectedCategory, Item item);

    public void saveNewItem(String url, ItemDto itemDto);

    public void deleteItem(String url);

    public List<ItemDto> getItemsByCategory(CategoryDto categoryDto);

    public void saveTextByUrl(String url, String text);


}
