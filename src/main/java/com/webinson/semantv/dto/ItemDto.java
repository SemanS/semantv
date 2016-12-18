package com.webinson.semantv.dto;

import com.webinson.semantv.entity.Category;
import lombok.Data;

import java.util.Date;

/**
 * Created by Slavo on 10/17/2016.
 */
@Data
public class ItemDto {

    private Long id;
    private String header;
    private String text;
    private Date date;
    private String url;
    private Category category;
    private String img;
    private String duration;

}
