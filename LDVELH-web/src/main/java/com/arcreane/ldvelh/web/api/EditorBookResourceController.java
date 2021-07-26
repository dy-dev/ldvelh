package com.arcreane.ldvelh.web.api;

import com.arcreane.ldvelh.core.controller.MenuType;
import com.arcreane.ldvelh.core.model.Book;
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
import java.util.List;

@RestController
@RequestMapping("/api/book")
@Getter
@Setter
public class EditorBookResourceController {

    @Autowired
    private IService service;

    /**
     * Method called when arriving to the url  : /ldevelh/api/book
     * returns all books in library
     * @return List of existing books
     */
    @GetMapping()
    public Iterable<Book> list() {
        return  service.getExistingBookList();
    }
//
//    /***
//     * Called when user click on the home page's link to a book
//     * Displays all detail about the book which title is passed has a parameter
//     * @param bookTitle
//     * @param model
//     * @return
//     */
    @GetMapping("/{title}")
    public Book detail(@PathVariable("title") String bookTitle) {
        return service.getBookWithTitle(bookTitle);
    }

//    @GetMapping("/create-form")
//    public String displayForm(@ModelAttribute("bookToFill") BookForm bookToFill) {
//        return "editor-create";
//    }
//
//    /***
//     * Method called when user wants to create a new book through the url
//     * /ldvelh/editor/create
//     * @return
//     */
//    @PostMapping("")
//    public String createBook(@Valid @ModelAttribute("bookToFill") BookForm bookToFill, BindingResult result) {
//        if(result.hasErrors())
//            return "editor-create";
//        else {
//            service.addBookToLibrary(BookForm.convertToBook(bookToFill));
//            return "editor-validate-create";
//        }
//    }
//
//    public String getUserSelection() {
//        return null;
//    }
//
//    public void startApp() {
//
//    }
//
//    public void showMenu(MenuType type) {
//
//    }
//
//    public void modifyBook() {
//
//    }
//
//    public void createNewChapter() {
//
//    }
//
//    public void saveChanges() {
//
//    }
//
//    public void linkOptions() {
//
//    }
//
//    public void setService(IService myService) {
//
//    }
}
