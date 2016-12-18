package com.webinson.semantv.service;

import com.webinson.semantv.dto.ItemDto;

import java.util.List;

/**
 * Created by Slavo on 10/17/2016.
 */
public interface ItemService {

    public List<ItemDto> getAllItems();

    public String getTextOfItemByUrl(String url);

    public String getImgOfItemByUrl(String url);

    public ItemDto getItemByUrl(String url);

    public void saveImgByUrl(String url, String img);

    public void saveItemByUrl(String url, ItemDto itemDto);

    public void saveNewItem(String url, String img);

    public void deleteItem(String url);

}