package com.arcreane.ldvelh.controller;

import com.arcreane.ldvelh.service.IService;

public interface IController {
    String getUserSelection();

    void startApp();

    void showMenu(MenuType type);

    void createBook();

    void modifyBook();

    void createNewChapter();

    void saveChanges();

    void linkOptions();

    void setService(IService myService);
}
