package com.arcreane.ldvelh.core.service.ocr;

import com.arcreane.ldvelh.core.model.Book;
import com.arcreane.ldvelh.core.model.Chapter;
import com.arcreane.ldvelh.core.repository.IRepository;
import com.arcreane.ldvelh.core.service.IService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

//@Service
@Getter @Setter @NoArgsConstructor
public class OCREditorService implements IService {
    @Autowired
    IRepository repository;

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
     /*   for(var chapter : book.getChapters().values()){
            for(var options : chapter.getOptions().entrySet()){
                chapter.getOptions().put(options.getKey(), book.getChapterById(options.getKey()));
            }
        }*/
    }

    @Override
    public void addBookToLibrary(Book book) {
        repository.save(book);
    }

    @Override
    public void saveBookContent(Book book) {
        //repository.updateTest(book);
    }

    @Override
    public List<Book> getExistingBookList() {
        return null;
    }

    @Override
    public Book getBookWithTitle(String bookTitle) {
        return null;
    }
}
