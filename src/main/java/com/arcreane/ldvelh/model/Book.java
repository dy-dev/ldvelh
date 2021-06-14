package com.arcreane.ldvelh.model;

import java.util.*;

/**
 * 
 */
public class Book {

    private String title;
    private Set<Tags> types;
    private Map<Integer, Chapter> chapters;

    public Book(){
        this("");
    }

    public Book(String title) {
        this.title = title;
        chapters = new HashMap<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Tags> getTypes() {
        return types;
    }

    public void setTypes(Set<Tags> types) {
        this.types = types;
    }

    public Map<Integer, Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(Map<Integer, Chapter> chapters) {
        this.chapters = chapters;
    }

    public void addChapter(Chapter chapter) {
        chapters.put(chapter.getId(), chapter);
    }
}