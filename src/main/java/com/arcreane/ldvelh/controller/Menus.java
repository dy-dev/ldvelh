package com.arcreane.ldvelh.controller;

import com.arcreane.ldvelh.controller.console.ConsoleController;

/**
 * Class used to display all necessary menu when manipulating the
 * model in a console application
 */
public class Menus {

    public void displayMainMenu(ConsoleController controller) {
        System.out.println("Select your option");
        int i = 0;
        System.out.println("-1 : To quit the application");
        System.out.println((++i) + " : Create a new book");
        System.out.println((++i) + " : Modify an existing book");
        System.out.println((++i) + " : Delete a book");
        System.out.println((++i) + " : Publish a book");
        System.out.println((++i) + " : Save book");

        String choice = controller.getUserSelection();

        switch (choice) {
            case "-1" -> controller.quitMenu(MenuType.MAIN);
            case "1" -> controller.createBook();
            case "2" -> controller.modifyBook();
            case "5" -> controller.saveChanges();
        }

    }

    public void displayEditBookMenu(ConsoleController controller) {
        System.out.println("Select your option");
        int i = 0;
        System.out.println("-1 : Back To Previous Menu");
        System.out.println((++i) + " : Create A New Chapter");
        System.out.println((++i) + " : Modify An Existing Chapter");
        System.out.println((++i) + " : Delete A Chapter");
        System.out.println((++i) + " : Define End State");
        System.out.println((++i) + " : Save Changes");

        String choice = controller.getUserSelection();

        switch (choice) {
            case "-1" -> controller.quitMenu(MenuType.BOOK);
            case "1" -> controller.createNewChapter();
            case "2" -> controller.modifyExistingChapter();
            case "5" -> controller.saveChanges();
        }
    }

    public void displayEditChapterMenu(ConsoleController controller) {
        System.out.println("Select your option");
        int i = 0;
        System.out.println("-1 : Back to previous menu");
        System.out.println((++i) + " : Add - Modify Text");
        System.out.println((++i) + " : Add - Modify Caption");
        System.out.println((++i) + " : Add Chapter Link Options");
        System.out.println((++i) + " : Change Intro Status");
        System.out.println((++i) + " : Change End Status");
        System.out.println((++i) + " : Add New Chapter");
        System.out.println((++i) + " : Save Changes");

        String choice = controller.getUserSelection();

        switch (choice) {
            case "-1" -> controller.quitMenu(MenuType.CHAPTER);
            case "1" -> controller.addTextTochapter();
            case "2" -> controller.addCaptionTochapter();
            case "3" -> controller.linkOptions();
            case "6" -> controller.createNewChapter();
            case "7" -> controller.saveChanges();
        }
    }
}
