package com.arcreane.ldvelh.controller;

import com.arcreane.ldvelh.core.controller.IController;
import com.arcreane.ldvelh.core.controller.MenuType;
import com.arcreane.ldvelh.core.model.Book;
import com.arcreane.ldvelh.core.service.IService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/editor")
@Getter
@Setter
public class EditorBookController implements IController {

    @Autowired
    private IService service;


    //    @RequestMapping("/error")
//    public RedirectView displayError(){
//        System.out.println("On est dans les erreurs" );
//        RedirectView rv = new RedirectView();
//        rv.setUrl("home");
//        return rv;
//    }
//
    @GetMapping("/error")
    public String displayError() {
        return "error.html";
    }

    /**
     * Method called when arriving to the url  : /ldevelh/editor/home
     * Displays all books in library
     *
     * @param model
     * @return
     */
    @GetMapping("/home")
    public String display(Model model) {
        System.out.println("C'est le bon chemin");
        model.addAttribute("BookList", service.getExistingBookList());
        return "editor-home";
    }

    /***
     * Called when user click on the home page's link to a book
     * Displays all detail about the book which title is passed has a parameter
     * @param bookTitle
     * @param model
     * @return
     */
    @GetMapping("/{title}")
    public String displayDetails(@PathVariable("title") String bookTitle, Model model) {
        System.out.println("On est dans les titles");
        var book = service.getBookWithTitle(bookTitle);
        if (book != null) {
            model.addAttribute("book", book);
            return "editor-details";
        }
        else return "redirect:/editor/home";
    }

    @GetMapping("/create-form")
    public String displayForm(@ModelAttribute("bookToFill") Book bookToFill){
        return "editor-create";
    }
    /***
     * Method called when user wants to create a new book through the url
     * /ldvelh/editor/create
     * @return
     */
    @Override
    @PostMapping("")
    public String createBook(@ModelAttribute("bookToFill") Book bookToFill) {
        service.addBookToLibrary(bookToFill);
        return "editor-validate-create";
    }

    @Override
    public String getUserSelection() {
        return null;
    }

    @Override
    public void startApp() {

    }

    @Override
    public void showMenu(MenuType type) {

    }




    @Override
    public void modifyBook() {

    }

    @Override
    public void createNewChapter() {

    }

    @Override
    public void saveChanges() {

    }

    @Override
    public void linkOptions() {

    }

    @Override
    public void setService(IService myService) {

    }
}
