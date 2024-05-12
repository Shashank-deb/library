package com.shashank.library.service.impl;

import com.shashank.library.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookValidation {

    @Autowired
    ValidationService validationService;

    public void setValidationService(ValidationService validationService){
        this.validationService=validationService;
    }

    public boolean validateBook(Book book){
        return validationService.validate(book);
    }
}