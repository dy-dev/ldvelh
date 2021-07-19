package com.arcreane.ldvelh.service;

import com.arcreane.ldvelh.model.Book;
import com.arcreane.ldvelh.model.Chapter;
import com.arcreane.ldvelh.repository.IRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
@Getter @Setter @NoArgsConstructor
public class OCREditorService implements IService{
    @Autowired
    IRepository repository;

    @Override
    public void addBookCover(Book scannedBook) {
        repository.saveCover(scannedBook);
    }

    @Override
    public Chapter addChapter(Book book) {
        Chapter chapter = new Chapter();
        //recoginze text and caption
        chapter.setText("Recognized text");
        chapter.setCaption("Recognized caption");

        //Link are in bold in text that is how they are recognized
        chapter.addOption(1);
        book.addChapter(chapter);
        return chapter;
    }

    /*public void checkProgress() {
        repository.loadSavePages();
    }*/

    public void parseBookForMissingChapter(Book book) {
        for(var chapter : book.getChapters().values()){
            for(var options : chapter.getOptions().entrySet()){
                chapter.getOptions().put(options.getKey(), book.getChapterById(options.getKey()));
            }
        }
    }

    @Override
    public void addBookToLibrary(Book book) {
        repository.addBook(book);
    }

    @Override
    public void saveBookContent(Book book) {
        repository.saveBook(book);
    }

    @Override
    public String[] getExistingBookList() {
        return new String[0];
    }

    @Override
    public Book getBookWithTitle(String bookTitle) {
        return null;
    }
}
