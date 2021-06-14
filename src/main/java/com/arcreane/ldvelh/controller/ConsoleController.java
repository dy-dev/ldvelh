package com.arcreane.ldvelh.controller;

import com.arcreane.ldvelh.model.Book;
import com.arcreane.ldvelh.model.Chapter;
import com.arcreane.ldvelh.service.EditorService;
import com.arcreane.ldvelh.service.PlayerService;

import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class ConsoleController {
    private Menus menus;
    private Scanner scan;

    private boolean keepEditing;
    private boolean editBook;
    private boolean editChapter;

    private Book currentBook;
    private Chapter currentChapter;
    private EditorService editorService;
    private PlayerService playerService;

    public ConsoleController() {
        this(new EditorService(), new PlayerService());
    }

    public ConsoleController(EditorService editorService, PlayerService playerService) {
        this.editorService = editorService;
        this.playerService = playerService;
        menus = new Menus();
        keepEditing = true;
        scan = new Scanner(System.in);
        currentBook = null;
        currentChapter = null;
    }

    public boolean isKeepEditing() {
        return keepEditing;
    }

    public boolean isEditBook() {
        return editBook;
    }

    public boolean isEditChapter() {
        return editChapter;
    }

    public void startApp() {
        while (!keepEditing) {
            menus.displayMainMenu(this);
        }
        scan.close();
    }


    public String getUserSelection() {
        return scan.nextLine();
    }

    public void quitMenu(MenuType type) {
        switch (type) {
            case MAIN -> {
                keepEditing = true;
            }
            case BOOK -> {
                editBook = false;
            }
            case CHAPTER -> {
                editChapter = false;
            }
        }
    }

    public void createBook() {
        System.out.println("What is the book title?");
        String title = scan.nextLine();
        currentBook = new Book(title);

        editorService.addBookToLibrary(currentBook);
        
        showMenu(MenuType.BOOK);
    }

    public void showMenu(MenuType type) {
        Consumer<ConsoleController> menu = null;
        Supplier<Boolean> condition = null;
        switch (type) {
            case MAIN -> {
                condition = this::isKeepEditing; //faire une référence sur une méthode permettant de connaitre la
                // valeur de l'attribut quit de l'instance "this" de la classe controller
                menu = menus::displayMainMenu; // faire une référence sur la méthode displayMainMenus
                // de l'instance menus de la classe Menu
            }
            case BOOK -> {
                editBook = true;
                condition = this::isEditBook;
                menu = menus::displayEditBookMenu;
            }
            case CHAPTER -> {
                editChapter = true;
                condition = this::isEditChapter;
                menu = menus::displayEditChapterMenu;
            }
        }
        while (condition.get()) {
            menu.accept(this);
        }
    }


    public void createNewChapter() {
        currentChapter = new Chapter();
        if (currentBook != null) {
            currentBook.addChapter(currentChapter);

            showMenu(MenuType.CHAPTER);
        } else {
            System.out.println("Warning you have not chosen the book to which add this new chapter");
        }

    }

    public void addTextTochapter() {
        if (currentChapter != null) {
            String text = scan.nextLine();
            currentChapter.setText(text);
        }
    }

    public void saveChanges() {
        if (currentBook != null)
            editorService.saveBook(currentBook);
    }
}
