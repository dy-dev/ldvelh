package com.arcreane.ldvelh.service;

import com.arcreane.ldvelh.model.Book;
import com.arcreane.ldvelh.repository.JSonRepository;

/**
 * Class used by the controller to manage all the editor functionnalities
 */
public class EditorService {

    /***
     * Repository used to serialize the information
     */
    JSonRepository repository;

    /**
     * Default constructor
     * The folder's name holding all the books has been chosen totally arbitrarily
     */
    public EditorService() {
        repository = new JSonRepository("Library");
    }

    /**
     * Add the book passed in parameter to the library
     * @param book
     */
    public void addBookToLibrary(Book book) {
        repository.addBook(book);
    }

    /**
     * Save the content of the book passed in parameter
     * @param book
     */
    public void saveBookContent(Book book) {
        repository.saveBook(book);
    }

    public String[] getExistingBookList() {
        return repository.listLibraryBooks();
    }

    /***
     * (Useless for now)
     * @param index
     * @return
     */
    public Book getBookAtIndex(int index) {
        return repository.getBook(index);
    }

    /**
     * Method to retrieve a book in repository from its name
     * @param bookTitle
     * @return
     */
    public Book getBookWithTitle(String bookTitle) {
        return repository.findBookWithTitle(bookTitle);
    }
}
