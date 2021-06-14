package com.arcreane.ldvelh.service;

import com.arcreane.ldvelh.model.Book;
import com.arcreane.ldvelh.repository.JSonRepository;

public class EditorService {

    JSonRepository repository;

    public EditorService() {
        repository = new JSonRepository("Library");
    }

    public void addBookToLibrary(Book book) {
        repository.addBook(book);
    }

    public void saveBook(Book book) {
        repository.saveBook(book);
    }
}
