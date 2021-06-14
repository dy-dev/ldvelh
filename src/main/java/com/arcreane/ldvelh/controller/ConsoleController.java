package com.arcreane.ldvelh.controller;

import com.arcreane.ldvelh.model.Book;
import com.arcreane.ldvelh.model.Chapter;
import com.arcreane.ldvelh.service.EditorService;
import com.arcreane.ldvelh.service.PlayerService;

import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Class to control the flow of the data.
 * In this case, allow the user to interact with the model
 */
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

    /**
     * Default constructor
     */
    public ConsoleController() {
        this(new EditorService(), new PlayerService());
    }

    /**
     * Constructor taking  2 parameters
     *
     * @param editorService
     * @param playerService
     */
    public ConsoleController(EditorService editorService, PlayerService playerService) {
        this.editorService = editorService;
        this.playerService = playerService;
        menus = new Menus();
        keepEditing = true;
        scan = new Scanner(System.in);
        currentBook = null;
        currentChapter = null;
    }

    /***
     * List of getter / setter
     * Part of java beans philosophy
     */
    public boolean isKeepEditing() {
        return keepEditing;
    }

    public boolean isEditBook() {
        return editBook;
    }

    public boolean isEditChapter() {
        return editChapter;
    }

    /**
     * Method used to access users feedback
     */
    public String getUserSelection() {
        return scan.nextLine();
    }

    /**
     * Entry function to start the interaction
     */
    public void startApp() {
        showMenu(MenuType.MAIN);
        scan.close();
    }

    /**
     * Method to manage what menu to display and how to stop it
     * @param type
     */
    public void showMenu(MenuType type) {
        //The menu is a reference to the method to call depending on the menu type passed in parameter
        Consumer<ConsoleController> menu = null;
        //The condition is a reference to the method to call to know when to stop displaying the menu
        Supplier<Boolean> condition = null;
        switch (type) {
            case MAIN -> {
                condition = this::isKeepEditing;
                menu = menus::displayMainMenu;
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

    /**
     * Method called when user wants to quit a menu
     * The parameter tells which menu to quit
     * @param type
     */
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

    /**
     * Method called when the user wants to create a book
     */
    public void createBook() {
        System.out.println("What is the book title?");
        String title = scan.nextLine();
        currentBook = new Book(title);
        editorService.addBookToLibrary(currentBook);
        showMenu(MenuType.BOOK);
    }


    /**
     * Method called when the user wants to create a new chapter to the current book
     */
    public void createNewChapter() {
        currentChapter = new Chapter();
        if (currentBook != null) {
            currentBook.addChapter(currentChapter);

            showMenu(MenuType.CHAPTER);
        } else {
            System.out.println("Warning you have not chosen the book to which add this new chapter");
        }

    }


    /**
     * Method called when the user wants to add text to an existing chapter
     */
    public void addTextTochapter() {
        if (currentChapter != null) {
            String text = scan.nextLine();
            currentChapter.setText(text);
        }
    }

    /**
     * Method called when the user wants to save his changes
     */
    public void saveChanges() {
        if (currentBook != null)
            editorService.saveBook(currentBook);
    }
}
