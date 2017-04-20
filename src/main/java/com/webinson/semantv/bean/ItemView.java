package com.webinson.semantv.bean;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLBeanName;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import com.webinson.semantv.dto.CategoryDto;
import com.webinson.semantv.dto.ItemDto;
import com.webinson.semantv.service.CategoryService;
import com.webinson.semantv.service.ItemService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Created by Slavo on 10/4/2016.
 */
@Component
@RequestScoped
@URLBeanName("itemView")
@URLMappings(mappings = {
        @URLMapping(
                id = "home",
                pattern = "/",
                viewId = "/index.xhtml"),
        @URLMapping(
                id = "login",
                pattern = "/login",
                viewId = "/login.xhtml"),
        @URLMapping(
                id = "items",
                pattern = "/items",
                viewId = "/items.xhtml"),
        @URLMapping(
                id = "item-detail",
                pattern = "/items/#{ itemUrl : itemView.itemUrl }",
                viewId = "/itemDetail.xhtml"),
        @URLMapping(
                id = "dashboard",
                pattern = "/dashboard",
                viewId = "/dashboard.xhtml"),
        @URLMapping(
                id = "dashboard-category",
                pattern = "/add-category",
                viewId = "/addnewcategory.xhtml"),
        @URLMapping(
                id = "dashboard-detail",
                pattern = "/dashboard/#{ itemUrl : itemView.itemUrl}",
                viewId = "/dashboarditemdetail.xhtml")
})
public class ItemView implements Serializable {

    @Autowired
    ItemService itemService;

    @Autowired
    CategoryService categoryService;

    @Getter
    @Setter
    private String selectedCategory;

    @Getter
    @Setter
    private ItemDto selectedItem;

    @Getter
    @Setter
    private List<ItemDto> items;

    @Getter
    @Setter
    private List<CategoryDto> categories;

    @Getter
    @Setter
    private String itemUrl;

    @Getter
    @Setter
    private String selectedUser;

    @URLAction
    public String loadItem() throws IOException {

        if (itemUrl != null) {
            this.selectedItem = itemService.getItemByUrl(itemUrl);
            return selectedItem.getUrl();
        }

        // Add a message here, "The item {..} could not be found."
        return "";
    }

    @PostConstruct
    public void init() {

        categories = categoryService.getAllCategories();

    }

    public List<ItemDto> allItemsByCategory(CategoryDto categoryDto) {

        return itemService.getItemsByCategory(categoryDto);
    }

    public void onItemDetail(ItemDto itemDto) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("/items/" + itemDto.getUrl());
    }

    public void onDashboardItemDetail(ItemDto itemDto) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("/dashboard/" + itemDto.getUrl());
    }

    public void newDashboardItem() throws IOException {
        ItemDto itemDto = new ItemDto();
        SecureRandom random = new SecureRandom();
        itemDto.setUrl(new BigInteger(130, random).toString(32));
        /*itemDto.setImg("images/newItem.jpg");*/

        Calendar calendar = Calendar.getInstance();
        /*java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());*/
        itemDto.setTimeStamp(Calendar.getInstance());
        itemDto.setCategory(categoryService.getCategory());

        itemService.saveNewItem(itemDto.getUrl(), itemDto);
        FacesContext.getCurrentInstance().getExternalContext().redirect("/dashboard/" + itemDto.getUrl());
    }

    public void deleteItem(ItemDto itemDto) throws IOException {

        itemService.deleteItem(itemDto.getUrl());
        FacesContext.getCurrentInstance().getExternalContext().redirect("/dashboard");
    }

    public String trimHeader(String header) {
        if (header.length() > 10) {
            return header.substring(0, 11) + " ...";
        }

        return header + " ...";
    }

}
