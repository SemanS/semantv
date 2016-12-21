/*
package com.webinson.semantv.bean;

import com.ocpsoft.pretty.PrettyContext;
import com.webinson.semantv.dto.CategoryDto;
import com.webinson.semantv.dto.ItemDto;
import com.webinson.semantv.entity.Category;
import com.webinson.semantv.service.CategoryService;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.context.Theme;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

*/
/**
 * Created by Slavo on 12/20/2016.
 *//*


@Component
public class OrderListView {

    @Autowired
    CategoryService categoryService;

    @Getter
    @Setter
    private List<CategoryDto> categories;

    @Getter
    @Setter
    private String selectedCategory;

    @PostConstruct
    public void init() {
        categories = categoryService.getAllCategories();
    }

    public String saveCategory() {

        categoryService.saveCategory(selectedCategory);
        return "pretty:dashboard";
    }

    public void onSelect(SelectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Selected", event.getObject().toString()));
        System.out.println(event.getObject().toString());
    }

    public void onUnselect(UnselectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Unselected", event.getObject().toString()));
    }

    public void onReorder() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "List Reordered", null));
    }

}
*/
