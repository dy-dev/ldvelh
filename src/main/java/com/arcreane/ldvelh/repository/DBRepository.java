package com.arcreane.ldvelh.repository;

import com.arcreane.ldvelh.model.Book;
import com.arcreane.ldvelh.model.Chapter;

public class DBRepository {
    public DBRepository() {
    }

    public void saveBookToDatabase(Book book) {
        //Access to database and save book information
    }

    public void recordChanges() {
        //Save changes to database
    }

    public void loadSavePages() {
        //access to the database to retrieve the work done up until now
    }

    public void addCoverToDB() {
        // Save image as big object
    }

    public void addChapter(Chapter chapter) {
        //Save chapter information
    }
}
