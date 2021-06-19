package com.arcreane.ldvelh.controller;

import com.arcreane.ldvelh.model.Book;
import com.arcreane.ldvelh.service.OCREditorService;

public class OCRController {
    OCREditorService ocrService;
    Book scannedBook;
    public OCRController() {
    }

    public void startScan() {
        ocrService = new OCREditorService();
        initScanBook();
        scanCover();
        System.out.println("Press end button to finish scan, press next to start scanning a new chapter");
        boolean end = false;
        while(!end){
            //Mecanism to catch the scanner end / next button
            scanNewChapter();
        }
        fillMissingChapter();
        saveBook();
    }


    public void initScanBook(){
        System.out.println("Scan the cover");
        scannedBook = new Book();
    }

    public void scanCover(){
        System.out.println("Cover scanned");
        ocrService.scanBookCover(scannedBook);

    }

    public void scanNewChapter(){
        int chapterNumber = ocrService.scanChapter(scannedBook);
        System.out.println("Chapter " + chapterNumber + " scanned");
        ocrService.saveChanges();
    }

    private void fillMissingChapter() {
        ocrService.parseBookForMissingChapter(scannedBook);
    }

    private void saveBook() {
        ocrService.saveChanges();
    }

}
