package com.arcreane.ldvelh.repository;

import com.arcreane.ldvelh.model.Book;

public interface IRepository {
    void addBook(Book book);
    
    void saveBook(Book book);

    String[] listLibraryBooks();

    Book getBook(int index);

    Book findBookWithTitle(String bookTitle);

    void saveCover(Book book);
}
