package com.arcreane.ldvelh.repository.json;

import com.arcreane.ldvelh.model.Book;
import com.arcreane.ldvelh.repository.IRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

/**
 * Repository in the form of JSon files saved on disk
 */
@Repository
@Getter @Setter @NoArgsConstructor
public class JSonRepository implements IRepository {
    @Value("${repository.config}")
    private String path;

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
    @Override
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
    @Override
    public void saveBook(Book book) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(path+ "\\" +book.getTitle()+"\\content.json"), book);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String[] listLibraryBooks() {
        File directory = new File(path);
        return directory.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                return new File(current, name).isDirectory();
            }
        });
    }

    /**
     * (Useless for now)
     * @param index
     * @return
     */
    @Override
    public Book getBook(int index) {
        return null;
    }

    /***
     * Retrieves a book in library by using its name
     *
     * @param bookTitle
     * @return
     */
    @Override
    public Book findBookWithTitle(String bookTitle) {
        String bookDirName = path + "\\" + bookTitle;
        File directory = new File(bookDirName);
        if (!directory.exists()) {
            return null;
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Book book = objectMapper.readValue(new File(bookDirName+"\\content.json"), Book.class);
            return book;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void saveCover(Book book) {

    }
}
