package com.arcreane.ldvelh.core.controller.console;

import com.arcreane.ldvelh.core.controller.IController;
import com.arcreane.ldvelh.core.controller.MenuType;
import com.arcreane.ldvelh.core.controller.Menus;
import com.arcreane.ldvelh.core.model.Book;
import com.arcreane.ldvelh.core.model.Chapter;
import com.arcreane.ldvelh.core.service.IService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Class to control the flow of the data.
 * In this case, allow the user to interact with the model
 */
//@Controller
@Getter @Setter
public class ConsoleController implements IController {

    private Menus menus;
    private Scanner scan;

    private boolean keepEditing;
    private boolean editBook;
    private boolean editChapter;

    private Book currentBook;
    private Chapter currentChapter;
    @Autowired
    private IService service;
//    private PlayerService playerService;

    /**
     * Default constructor
     */
    public ConsoleController(/*PlayerService playerService*/) {
//        this.playerService = playerService;
        menus = new Menus();
        keepEditing = true;
        scan = new Scanner(System.in);
        currentBook = null;
        currentChapter = null;
    }

    /**
     * Method used to access users feedback
     */
    @Override
    public String getUserSelection() {
        return scan.nextLine();
    }

    /**
     * Entry function to start the interaction
     */
    @Override
    public void startApp() {
        showMenu(MenuType.MAIN);
        scan.close();
    }

    //region Menu Management
    /**
     * Method to manage what menu to display and how to stop it
     *
     * @param type
     */
    @Override
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
     *
     * @param type
     */
    public void quitMenu(MenuType type) {
        switch (type) {
            case MAIN -> {
                keepEditing = false;
            }
            case BOOK -> {
                editBook = false;
            }
            case CHAPTER -> {
                editChapter = false;
            }
        }
    }
    //endregion

    //region Book Management

    /**
     * Method called when the user wants to create a book
     */
    @Override
    public void createBook() {
        System.out.println("What is the book title?");
        String title = scan.nextLine();
        currentBook = new Book(title);
        service.addBookToLibrary(currentBook);
        showMenu(MenuType.BOOK);
    }

    /***
     * Method called to modify a book
     * Start by displaying all available books, then ask the user to select one
     */
    @Override
    public void modifyBook() {
        int i = 0;
        var bookList = service.getExistingBookList();
        for (var book : bookList) {
            System.out.println((i++) + " : " + book);
        }
        int index = Integer.parseInt(scan.nextLine());
        String bookTitle ="";// bookList.get(index).getTitle();
        currentBook = service.getBookWithTitle(bookTitle);
        service.parseBookForMissingChapter(currentBook);
        System.out.println(currentBook);
        showMenu(MenuType.BOOK);
    }


    /**
     * Method called when the user wants to create a new chapter to the current book
     */
    @Override
    public void createNewChapter() {
        currentChapter = new Chapter();
        if (currentBook != null) {
            currentChapter = service.addChapter(currentBook);
            showMenu(MenuType.CHAPTER);
        } else {
            System.out.println("Warning you have not chosen the book to which add this new chapter");
        }

    }

    //end region
    //endregion

    //region Chapter Management

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
     * Method called when the user wants to add caption to an existing chapter
     */
    public void addCaptionTochapter() {
        if (currentChapter != null) {
            String caption = scan.nextLine();
            currentChapter.setCaption(caption);
        }
    }

    /**
     * Method called when the user wants to save his changes
     */
    @Override
    public void saveChanges() {
        if (currentBook != null)
            service.saveBookContent(currentBook);
    }

    @Override
    public void linkOptions() {
        System.out.println("Enter the chapters id to which the current chapter can lead to (separate the id with commas) ");
        displayChapterList();
        var chapterIndexes = scan.nextLine().split(",");
        if (chapterIndexes.length > 0) {
            for (var index : chapterIndexes) {
                var chapter = currentBook.getChapterById(Integer.parseInt(index));
                if (chapter == null) {
                    System.out.println("Chapter does not exists, type \"y\" to create it or any other key not to ? " +
                            "(chapterId will be modified because they are automatically generated)");
                    if (scan.nextLine().equals("y")) {
                        System.out.println("Please enter caption");
                        String caption = scan.nextLine();
                        System.out.println("Please enter text");
                        String text = scan.nextLine();
                        chapter = new Chapter(caption, text);
                        currentBook.addChapter(chapter);
                    }
                }
                if (chapter != null)
                    currentChapter.addOption(chapter);
            }
        }
    }

    @Override
    public void setService(IService myService) {
        service = myService;
    }

    /***
     * Utility function to display all chapters in the form
     * id : caption
     * to ease the user's selection
     */
    private void displayChapterList() {
        var chapterList = currentBook.getChapters();
        for (var chapter :chapterList.values()) {
            System.out.println(chapter.getId() + " : " + chapter.getCaption());
        }
    }

    /***
     * Method called to modify an existing chapter
     * The user first see all the available chapter thanks to the call to
     * {@link #displayChapterList()} then select the one it wants to modify
     */
    public void modifyExistingChapter() {
        displayChapterList();
        var chapterIndex = Integer.parseInt(scan.nextLine());
        currentChapter = currentBook.getChapterById(chapterIndex);
        showMenu(MenuType.CHAPTER);
    }
    //endregion


}
