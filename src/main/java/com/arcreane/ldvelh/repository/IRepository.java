package com.arcreane.ldvelh.repository;

import com.arcreane.ldvelh.model.Book;

import java.util.List;

public interface IRepository {
    void addBook(Book book);
    
    void saveBook(Book book);

    List<Book> listLibraryBooks();

    Book getBook(int index);

    Book findBookWithTitle(String bookTitle);

    void saveCover(Book book);
}
