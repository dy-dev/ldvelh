package com.arcreane.ldvelh.web.controller;

import com.arcreane.ldvelh.core.controller.MenuType;
import com.arcreane.ldvelh.core.service.IService;
import com.arcreane.ldvelh.web.form.BookForm;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/editor")
@Getter
@Setter
public class EditorBookController {

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
        } else return "redirect:/editor/home";
    }

    @GetMapping("/create-form")
    public String displayForm(@ModelAttribute BookForm bookToFill) {
        return "editor-create";
    }

    /***
     * Method called when user wants to create a new book through the url
     * /ldvelh/editor/create
     * @return
     */
    @PostMapping("")
    public String createBook(@Valid @ModelAttribute BookForm bookToFill, BindingResult result) {
        if(result.hasErrors())
            return "editor-create";
        else {
            service.addBookToLibrary(BookForm.convertToBook(bookToFill));
            return "editor-validate-create";
        }
    }



    @GetMapping("/book-js")
    public String showJsHtml(){
        return "js-book-home";
    }


    public String getUserSelection() {
        return null;
    }

    public void startApp() {

    }

    public void showMenu(MenuType type) {

    }

    public void modifyBook() {

    }

    public void createNewChapter() {

    }

    public void saveChanges() {

    }

    public void linkOptions() {

    }

    public void setService(IService myService) {

    }
}
