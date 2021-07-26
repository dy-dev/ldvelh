package com.arcreane.ldvelh.core.repository.json;

import com.arcreane.ldvelh.core.model.Book;
import com.arcreane.ldvelh.core.repository.IRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repository in the form of JSon files saved on disk
 */
//@Repository
@Getter @Setter @NoArgsConstructor
public class JSonRepository implements IRepository {
    @Value("${repository.config}")
    private String path;

//    public JSonRepository(String saved_games) {
//    }

    /**
     * Constructor
     * It starts by checking if the library folder exists and if it doesn't it creates it
     *
     * @param subDir Folder where to save the files
     */
//    public JSonRepository(String subDir) {
//        path = System.getProperty("user.home") + "\\ldvelh\\" + subDir;
//        File directory = new File(path);
//        if (! directory.exists()){
//            System.out.println("Create path : " + path);
//            directory.mkdirs(); //This method create the whole folder hierarchy if needs be
//        }
//
//    }

    /**
     * Create the folder that will contains all files needed by the book (images, sound, items infos...)
     * Then saves the book by calling the {@link #saveBook(Book)} method
     * @param book
     */
    @Override
    public Book save(Book book) {
        String bookDirectory = path + "\\" + book.getTitle();
        File directory = new File(bookDirectory);
        directory.mkdir();
        saveBook(book);
        return book;
    }



    /**
     * Method calling the ObjectMapper of the Jackson Library to convert the javabean Book object
     * to a json file
     * @param book
     */
    private void saveBook(Book book) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            var f = new File(path+ "\\" +book.getTitle()+"\\content.json");
            objectMapper.writeValue(f, book);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Book> findAll() {
        File directory = new File(path);
        List<Book> bookList = new ArrayList<>();
        var titles = directory.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                return new File(current, name).isDirectory();
            }
        });
        for (var title:titles) {
           bookList.add(findBookByTitle(title)) ;
        }
        return bookList;
    }
    /**
     * (Useless for now)
     * @param index
     * @return
     */
    @Override
    public Optional<Book> findById(Integer index) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }


    /***
     * Retrieves a book in library by using its name
     *
     * @param bookTitle
     * @return
     */
    @Override
    public Book findBookByTitle(String bookTitle) {
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


    //region Unused Interface methods
    @Override
    public Iterable<Book> findAllById(Iterable<Integer> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Book book) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> iterable) {

    }

    @Override
    public void deleteAll(Iterable<? extends Book> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Book> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }
    //endregion


}
