package com.arcreane.ldvelh.repository;

import com.arcreane.ldvelh.model.Book;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 * Repository in the form of JSon files saved on disk
 */
public class JSonRepository {
    String path;

    /**
     * Constructor
     * It starts by checking if the library folder exists and if it doesn't it creates it
     *
     * @param subDir Folder where to save the files
     */
    public JSonRepository(String subDir) {
        path = System.getProperty("user.home") + "\\ldvelh\\" + subDir;
        File directory = new File(path);
        if (! directory.exists()){
            System.out.println("Create path : " + path);
            directory.mkdirs(); //This method create the whole folder hierarchy if needs be
        }

    }

    /**
     * Create the folder that will contains all files needed by the book (images, sound, items infos...)
     * Then saves the book by calling the {@link #saveBook(Book)} method
     * @param book
     */
    public void addBook(Book book) {
        String bookDirectory = path + "\\" + book.getTitle();
        File directory = new File(bookDirectory);
        directory.mkdir();
        saveBook(book);
    }

    /**
     * Method calling the ObjectMapper of the Jackson Library to convert the javabean Book object
     * to a json file
     * @param book
     */
    public void saveBook(Book book) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(path+ "\\" +book.getTitle()+"\\content.json"), book);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
