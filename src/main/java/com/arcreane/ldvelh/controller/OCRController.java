package com.arcreane.ldvelh.controller;

import com.arcreane.ldvelh.model.Book;
import com.arcreane.ldvelh.service.IService;
import com.arcreane.ldvelh.service.OCREditorService;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OCRController implements IController {
    IService service;
    Book scannedBook;
    public OCRController() {
    }

    @Override
    public void startApp() {
        createBook();
        scanCover();
        System.out.println("Press end button to finish scan, press next to start scanning a new chapter");
        boolean end = false;
        while(!end){
            //Mecanism to catch the scanner end / next button
            createNewChapter();
        }
        fillMissingChapter();
        saveBook();
    }



    public void scanCover(){
        System.out.println("Cover scanned");
        service.addBookCover(scannedBook);
    }

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
    public void createBook() {
        System.out.println("Scan the cover");
        scannedBook = new Book();
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
