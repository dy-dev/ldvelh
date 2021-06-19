package com.arcreane.ldvelh.service;

import com.arcreane.ldvelh.model.Book;
import com.arcreane.ldvelh.model.Chapter;
import com.arcreane.ldvelh.repository.DBRepository;

public class OCREditorService {
    DBRepository repository;

    public OCREditorService() {
        repository = new DBRepository();
    }

    public void scanBookCover(Book scannedBook) {
        //Process to save the cover in DB
        repository.addCoverToDB();
    }

    public void scanBook(Book book) {
        ///Process to get an image of the front cover and extract informations
        repository.saveBookToDatabase(book);
    }

    public int scanChapter(Book scannedBook) {
        Chapter chapter = new Chapter();
        //recoginze text and caption
        chapter.setText("Recognized text");
        chapter.setCaption("Recognized caption");

        //Link are in bold in text that is how they are recognized
        chapter.addOption(1);
        repository.addChapter(chapter);
        return 0;
    }

    public void saveChanges() {
        repository.recordChanges();
    }

    public void checkProgress() {
        repository.loadSavePages();
    }

    public void parseBookForMissingChapter(Book scannedBook) {
        for(var chapter : scannedBook.getChapters().values()){
            for(var options : chapter.getOptions().entrySet()){
                chapter.getOptions().put(options.getKey(), scannedBook.getChapterById(options.getKey()));
            }
        }
    }

}
