package com.arcreane.ldvelh.core.controller;

import com.arcreane.ldvelh.core.model.Book;
import com.arcreane.ldvelh.core.service.IService;

public interface IController {
    String getUserSelection();

    void startApp();

    void showMenu(MenuType type);

    String createBook(Book book);

    void modifyBook();

    void createNewChapter();

    void saveChanges();

    void linkOptions();

    void setService(IService myService);
}
