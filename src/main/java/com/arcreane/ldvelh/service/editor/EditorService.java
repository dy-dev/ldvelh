package com.arcreane.ldvelh.service.editor;

import com.arcreane.ldvelh.model.Book;
import com.arcreane.ldvelh.model.Chapter;
import com.arcreane.ldvelh.repository.IRepository;
import com.arcreane.ldvelh.service.IService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Class used by the controller to manage all the editor functionnalities
 */
@Service
@Getter @Setter @NoArgsConstructor
public class EditorService implements IService {
    private static int globalIndex;

    /***
     * Repository used to serialize the information
     */
    @Autowired
    IRepository repository;

    /**
     * Add the book passed in parameter to the library
     *
     * @param book
     */
    @Override
    public void addBookToLibrary(Book book) {
        repository.addBook(book);
    }

    @Override
    public void parseBookForMissingChapter(Book book) {
        globalIndex = book.getGlobalIndexValue();
        for (var chapter : book.getChapters().values()) {
            for (var optionId : chapter.getIndexes()) {
                chapter.addOption(book.getChapterById(optionId));
            }
        }
    }

    /**
     * Save the content of the book passed in parameter
     *
     * @param book
     */
    @Override
    public void saveBookContent(Book book) {
        repository.saveBook(book);
    }

    @Override
    public List<Book> getExistingBookList() {
        return repository.listLibraryBooks();
    }

    /***
     * (Useless for now)
     * @param index
     * @return
     */
    public Book getBookAtIndex(int index) {
        return repository.getBook(index);
    }

    /**
     * Method to retrieve a book in repository from its name
     *
     * @param bookTitle
     * @return
     */
    @Override
    public Book getBookWithTitle(String bookTitle) {
        Book bookFound = repository.findBookWithTitle(bookTitle);
        globalIndex = bookFound.getGlobalIndexValue();
        return bookFound;
    }

    @Override
    public void addBookCover(Book scannedBook) {

    }

    @Override
    public Chapter addChapter(Book book) {
        Chapter chapter = new Chapter();
        book.addChapter(chapter);
        chapter.setId(globalIndex++);
        book.setGlobalIndexValue(globalIndex);
        return chapter;
    }
}
