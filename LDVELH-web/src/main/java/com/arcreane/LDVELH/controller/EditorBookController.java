package com.arcreane.LDVELH.controller;

import com.arcreane.ldvelh.controller.IController;
import com.arcreane.ldvelh.controller.MenuType;
import com.arcreane.ldvelh.model.Book;
import com.arcreane.ldvelh.service.IService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/editor")
@Getter @Setter
public class EditorBookController implements IController {

    @Autowired
    private IService service;

    //A partir du moment où on fait appel à Thymeleaf, les fichiers html "dynamiques" doivent être
    // dans le dossier templates

//    @RequestMapping("/display-home")
//    public String display(HttpServletRequest request){
//        System.out.println("Coucou on est passé par là");
//        //Il faut que la fonction renvoie le nom du fichier htm que thymeleaf va compléter
//        request.setAttribute("BookList",service.getExistingBookList());
//        return "display-home";
//    }

    @RequestMapping("/home")
    @ModelAttribute("BookList")
    public List<Book> display(){
        System.out.println("On passe ici aussi");
        return service.getExistingBookList();
    }

    @RequestMapping("/{title}")
    //@ModelAttribute("book")
    public ModelAndView displayDetails( @PathVariable("title") String bookTitle){
        ModelAndView mv = new ModelAndView("editBook");
        mv.addObject("book", service.getBookWithTitle(bookTitle));
        return mv;
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
    public void createBook() {

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
