package com.webinson.semantv.assembler;

import com.webinson.semantv.dto.ItemDto;
import com.webinson.semantv.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Slavo on 10/17/2016.
 */
@Component
public class ItemAssembler {

    @Autowired
    CategoryAssembler categoryAssembler;

    public ItemDto convertToDto(Item model, ItemDto dto) {
        dto.setHeader(model.getHeader());
        dto.setText(model.getText());
        dto.setDate(model.getDate());
        dto.setUrl(model.getUrl());
        dto.setCategory(categoryAssembler.toDto(model.getCategory()));
        dto.setImg(model.getImg());
        dto.setDuration(model.getDuration());
        dto.setItemDescription(model.getItemDescription());
        dto.setTimeStamp(model.getTimeStamp());
        return dto;
    }

    public ItemDto toDto(Item model) {
        ItemDto itemDto = new ItemDto();
        itemDto.setHeader(model.getHeader());
        itemDto.setText(model.getText());
        itemDto.setDate(model.getDate());
        itemDto.setUrl(model.getUrl());
        itemDto.setCategory(categoryAssembler.toDto(model.getCategory()));
        itemDto.setImg(model.getImg());
        itemDto.setDuration(model.getDuration());
        itemDto.setItemDescription(model.getItemDescription());
        itemDto.setTimeStamp(model.getTimeStamp());
        return itemDto;
    }

    public Item convertToModel(Item item, ItemDto itemDto) {
        item.setHeader(itemDto.getHeader());
        item.setText(itemDto.getText());
        item.setUrl(itemDto.getUrl());
        item.setDuration(itemDto.getDuration());
        item.setImg(itemDto.getImg());
        item.setCategory(categoryAssembler.toModel(itemDto.getCategory()));
        item.setItemDescription(itemDto.getItemDescription());
        item.setTimeStamp(itemDto.getTimeStamp());
        return item;
    }

    public List<ItemDto> toDtos(final Collection<Item> models) {
        final List<ItemDto> dtos = new ArrayList<>();
        if (isNotEmpty(models)) {
            for (final Item item : models) {
                dtos.add(convertToDto(item, new ItemDto()));
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
