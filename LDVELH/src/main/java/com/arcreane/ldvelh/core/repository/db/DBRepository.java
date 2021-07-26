//package com.arcreane.ldvelh.core.repository.db;
//
//import com.arcreane.ldvelh.core.model.Book;
//import com.arcreane.ldvelh.core.model.Chapter;
//import com.arcreane.ldvelh.core.repository.IRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.support.GeneratedKeyHolder;
//import org.springframework.jdbc.support.KeyHolder;
//import org.springframework.stereotype.Repository;
//
//import java.sql.PreparedStatement;
//import java.sql.Statement;
//import java.util.List;
//
//@Repository
//public class DBRepository implements IRepository {
//
//    @Autowired
//    JdbcTemplate jdbcTemplate;
//
//    public DBRepository() {
//    }
//
//    public void saveBookToDatabase(Book book) {
//        //Access to database and save book information
//    }
//
//    public void recordChanges() {
//        //Save changes to database
//    }
//
//    public void loadSavePages() {
//        //access to the database to retrieve the work done up until now
//    }
//
//    public void saveCover(Book book) {
//        // Save image as big object
//    }
//
//    public void addChapter(Chapter chapter) {
//        //Save chapter information
//    }
//
//    @Override
//    public Book addBook(Book book) {
//        KeyHolder kh = new GeneratedKeyHolder();
//        jdbcTemplate.update(connection -> {
//            PreparedStatement ps = connection.prepareStatement("INSERT INTO book(title) VALUES (?)",
//            Statement.RETURN_GENERATED_KEYS);
//            ps.setString(1, book.getTitle());
//            return ps;
//        }, kh);
//        book.setId(kh.getKey().intValue());
//        return book;
//    }
//
//    @Override
//    public void saveBook(Book book) {
//
//    }
//
//    @Override
//    public List<Book> listLibraryBooks() {
//        return jdbcTemplate.query("Select * From book",
//                (rs, rownum) -> new Book(rs.getString("title")));
//    }
//
//    @Override
//    public Book getBook(int index) {
//        return null;
//    }
//
//    @Override
//    public Book findBookWithTitle(String bookTitle) {
//        return jdbcTemplate.queryForObject("Select * From book WHERE title = ?",
//                new Object[]{bookTitle},
//                (rs, rownum) -> new Book(rs.getString("title")));
//    }
//}
