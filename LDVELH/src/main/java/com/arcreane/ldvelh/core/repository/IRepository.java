package com.arcreane.ldvelh.core.repository;

import com.arcreane.ldvelh.core.model.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IRepository extends CrudRepository<Book, Integer> {
    Book findBookByTitle(String title);

    //    Book addBook(Book book);
//
//    void saveBook(Book book);
//
//    List<Book> listLibraryBooks();
//
//    Book getBook(int index);
//
//    Book findBookWithTitle(String bookTitle);
//
//    void saveCover(Book book);
}
