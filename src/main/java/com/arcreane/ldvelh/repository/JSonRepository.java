package com.arcreane.ldvelh.repository;

import com.arcreane.ldvelh.model.Book;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JSonRepository {
    String path;

    public JSonRepository(String subDir) {
        path = System.getProperty("user.home") + "\\ldvelh\\" + subDir;
        File directory = new File(path);
        if (! directory.exists()){
            System.out.println("Create path : " + path);
            directory.mkdirs();
            // If you require it to make the entire directory path including parents,
            // use directory.mkdirs(); here instead.
        }

    }

    public void addBook(Book book) {
        String bookDirectory = path + "\\" + book.getTitle();
        File directory = new File(bookDirectory);
        directory.mkdir();
        saveBook(book);
    }

    public void saveBook(Book book) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(path+ "\\" +book.getTitle()+"\\content.json"), book);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
