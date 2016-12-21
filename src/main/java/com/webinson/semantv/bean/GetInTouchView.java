package com.webinson.semantv.bean;

import com.google.common.collect.Lists;
import it.ozimov.springboot.templating.mail.model.Email;
import it.ozimov.springboot.templating.mail.model.impl.EmailImpl;
import it.ozimov.springboot.templating.mail.service.EmailService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * Created by Slavo on 11/11/2016.
 */
@Component
@ViewScoped
public class GetInTouchView {

    @Autowired
    public EmailService emailService;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String sureName;

    @Getter
    @Setter
    private String textarea;

    @com.webinson.semantv.utils.Email(message = "Prosím, zadajte emailovú adresu v správnom tvare")
    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String phoneNumber;

    /*public String updateData() {
        System.out.println(name);
        return "confirmation?faces-redirect=true";
    }*/

    public String updateData() throws UnsupportedEncodingException {
        final Email email = EmailImpl.builder()
                .from(new InternetAddress("ceo@webinson.com", "Message from " + name + sureName))
                .to(Lists.newArrayList(new InternetAddress("ing.vladimir.seman@gmail.com", "Správa zo seman.tv")))
                .subject(name + sureName + phoneNumber)
                .body(getEmail() + textarea)
                .encoding(Charset.forName("UTF-8")).build();

        emailService.send(email);
        return "confirmation?faces-redirect=true";
    }

}
