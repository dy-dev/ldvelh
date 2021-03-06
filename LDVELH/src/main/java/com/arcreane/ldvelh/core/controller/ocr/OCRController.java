package com.arcreane.ldvelh.core.controller.ocr;

import com.arcreane.ldvelh.core.controller.IController;
import com.arcreane.ldvelh.core.controller.MenuType;
import com.arcreane.ldvelh.core.model.Book;
import com.arcreane.ldvelh.core.service.IService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

//@Controller
@Getter @Setter
public class OCRController implements IController {
    @Autowired
    IService service;
    Book scannedBook;
    public OCRController() {
    }

    @Override
    public void startApp() {
        createBook(null);
//        scanCover();
        System.out.println("Press end button to finish scan, press next to start scanning a new chapter");
        boolean end = false;
        while(!end){
            //Mecanism to catch the scanner end / next button
            createNewChapter();
        }
        fillMissingChapter();
        saveBook();
    }
//
//
//
//    public void scanCover(){
//        System.out.println("Cover scanned");
//        service.addBookCover(scannedBook);
//    }

    private void fillMissingChapter() {
        service.parseBookForMissingChapter(scannedBook);
    }

    private void saveBook() {
        service.saveBookContent(scannedBook);
    }

    @Override
    public String getUserSelection() {
        return null;
    }



    @Override
    public void showMenu(MenuType type) {

    }

    @Override
    public String createBook(Book book) {
        System.out.println("Scan the cover");
        scannedBook = new Book();
        return "";
    }

    @Override
    public void modifyBook() {

    }

    @Override
    public void createNewChapter() {
        var chapter = service.addChapter(scannedBook);
        System.out.println("Chapter " + chapter.getId() + " scanned");
        service.saveBookContent(scannedBook);
    }

    @Override
    public void saveChanges() {

    }

    @Override
    public void linkOptions() {

    }

    @Override
    public void setService(IService myService) {
        service = myService;
    }
}
