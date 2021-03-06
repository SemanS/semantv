package com.webinson.semantv.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Slavo on 10/16/2016.
 */

@Entity
@Table(name = "item", schema = "semantv")
@Data
@NoArgsConstructor
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "header")
    private String header;

    @Column(name = "text")
    private String text;

    @Column(name = "date")
    private Date date;

    @Column(name = "url")
    private String url;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "img")
    private String img;

    @Column(name = "duration")
    private String duration;

    @Column(name = "itemdescription")
    private String itemDescription;

    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar timeStamp;

    @Column(name = "img_base")
    private byte[] imgBase;
}
