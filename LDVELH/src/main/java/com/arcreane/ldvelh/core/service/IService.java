package com.arcreane.ldvelh.core.service;

import com.arcreane.ldvelh.core.model.Book;
import com.arcreane.ldvelh.core.model.Chapter;
import com.arcreane.ldvelh.core.repository.IRepository;
import java.util.List;

public interface IService {
    void addBookToLibrary(Book book);

    //This method needs to send back the list of books and
    //not only their titles
    List<Book> getExistingBookList();

    Book getBookWithTitle(String bookTitle);

    void saveBookContent(Book book);

    void parseBookForMissingChapter(Book book);

    void addBookCover(Book scannedBook);

    Chapter addChapter(Book currentBook);

    void setRepository(IRepository myRepository);
}
