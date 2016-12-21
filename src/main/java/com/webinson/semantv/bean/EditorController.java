package com.webinson.semantv.bean;

import com.ocpsoft.pretty.PrettyContext;
import com.webinson.semantv.dto.CategoryDto;
import com.webinson.semantv.dto.ItemDto;
import com.webinson.semantv.entity.Category;
import com.webinson.semantv.service.CategoryService;
import com.webinson.semantv.service.ItemService;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.io.FilenameUtils;

/**
 * Created by Slavo on 11/17/2016.
 */
@Component
@ViewScoped
public class EditorController implements Serializable {

    @Autowired
    CategoryService categoryService;

    @Getter
    @Setter
    private List<CategoryDto> categories;

    @Getter
    @Setter
    @ManagedProperty(value = "#{param.itemDto.url}")
    private ItemDto itemDto;

    @Getter
    @Setter
    private String text;

    @Getter
    @Setter
    private String selectedCategory;

    @Getter
    @Setter
    private String header;

    @Getter
    @Setter
    private String url;

    @Getter
    @Setter
    private String imgText;

    @Getter
    @Setter
    private Part file;

    @Getter
    @Setter
    private Part imgFile;

    @Autowired
    ItemService itemService;

    @PostConstruct
    public void init() {

        text = showText();
        itemDto = showItemDto();
        categories = categoryService.getAllCategories();
        selectedCategory = itemDto.getCategory().getName();
    }

    public ItemDto showItemDto() {
        String path = PrettyContext.getCurrentInstance().getRequestURL().toURL();
        String segments[] = path.split("/");
        String resultUrl = segments[segments.length - 1];

        return itemService.getItemByUrl(resultUrl);

    }

    public String saveItem() {
        String path = PrettyContext.getCurrentInstance().getRequestURL().toURL();
        String segments[] = path.split("/");
        String resultUrl = segments[segments.length - 1];

        itemService.saveItemByUrl(resultUrl, selectedCategory, itemDto);
        return "pretty:dashboard";
    }

    public String showText() {

        String path = PrettyContext.getCurrentInstance().getRequestURL().toURL();
        String segments[] = path.split("/");
        String resultUrl = segments[segments.length - 1];

        return itemService.getTextOfItemByUrl(resultUrl);
    }

    /*public void saveText() {

        //System.out.println(selectedCard);
        String path = PrettyContext.getCurrentInstance().getRequestURL().toURL();
        String segments[] = path.split("/");
        String resultUrl = segments[segments.length - 1];

        itemService.saveItemByUrl(resultUrl, itemDto);
    }*/

    public void saveImageText() {

        //System.out.println(selectedCard);
        String path = PrettyContext.getCurrentInstance().getRequestURL().toURL();
        String segments[] = path.split("/");
        String resultUrl = segments[segments.length - 1];

        itemService.saveImgByUrl(resultUrl, imgText);
    }

    public void uploadListener() throws Exception {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

        String fileName = FilenameUtils.getName(file.getSubmittedFileName());
        String fileNamePrefix = FilenameUtils.getBaseName(fileName) + "_";
        /*String fileNameSuffix = "." + "jpg";*/
        String fileNameSuffix = "." + FilenameUtils.getExtension(fileName);

        File uploadFolder = new File("C:\\Projects\\Webinson\\images");

        try {
            File result = File.createTempFile(fileNamePrefix, fileNameSuffix, uploadFolder);

            FileOutputStream fileOutputStream = new FileOutputStream(result);
            byte[] buffer = new byte[1024];
            int bulk;

            InputStream inputStream = file.getInputStream();
            while (true) {
                bulk = inputStream.read(buffer);
                if (bulk < 0) {
                    break;
                }

                fileOutputStream.write(buffer, 0, bulk);
                fileOutputStream.flush();
            }

            fileOutputStream.close();
            inputStream.close();

            setText(text + "<img src=\"/images/" + result.getName() + "\" />");

            if (header != "") {

            }

            RequestContext.getCurrentInstance().update("editor_input");

            FacesMessage msg = new FacesMessage("Succesful", file.getName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            FacesMessage error = new FacesMessage("The files were not uploaded!");
            FacesContext.getCurrentInstance().addMessage(null, error);
        }

    }

    public void uploadImageListener() throws Exception {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

        String fileName = FilenameUtils.getName(imgFile.getSubmittedFileName());
        String fileNamePrefix = FilenameUtils.getBaseName(fileName) + "_";
        /*String fileNameSuffix = "." + "jpg";*/
        String fileNameSuffix = "." + FilenameUtils.getExtension(fileName);

        File uploadFolder = new File("C:\\Projects\\Webinson\\images");

        try {
            File result = File.createTempFile(fileNamePrefix, fileNameSuffix, uploadFolder);

            FileOutputStream fileOutputStream = new FileOutputStream(result);
            byte[] buffer = new byte[1024];
            int bulk;

            InputStream inputStream = imgFile.getInputStream();
            while (true) {
                bulk = inputStream.read(buffer);
                if (bulk < 0) {
                    break;
                }

                fileOutputStream.write(buffer, 0, bulk);
                fileOutputStream.flush();
            }

            fileOutputStream.close();
            inputStream.close();

            setImgText("/images/" + result.getName());
            saveImageText();

            RequestContext.getCurrentInstance().update("editor_input");

            FacesMessage msg = new FacesMessage("Succesful", imgFile.getName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            FacesMessage error = new FacesMessage("The files were not uploaded!");
            FacesContext.getCurrentInstance().addMessage(null, error);
        }

    }

}
