package com.arcreane.ldvelh.model;

import java.util.*;

/**
 * 
 */
public class Book {

    private String title;
    private Set<Tags> types;
    private Map<Integer, Chapter> chapters;
    private int globalIndexValue;

    public Book(){
        this("");
    }

    public Book(String title) {
        this.title = title;
        chapters = new HashMap<>();
        types = new HashSet<>();
        types.add(Tags.CONTEMPORARY);
        globalIndexValue = 0;
        Chapter.setGlobalIndex(0);
   }


    //region Getter / Setter


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

    public int getGlobalIndexValue() {
        return globalIndexValue;
    }

    public void setGlobalIndexValue(int globalIndexValue) {
        this.globalIndexValue = globalIndexValue;
    }
    //endregion

    public void addChapter(Chapter chapter) {
        chapters.put(chapter.getId(), chapter);
        globalIndexValue = Chapter.getGlobalIndex();
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", nb chapters= " + chapters.size() +
                '}';
    }

    public Chapter getChapterById(int parseInt) {
        return chapters.get(parseInt);
    }


}