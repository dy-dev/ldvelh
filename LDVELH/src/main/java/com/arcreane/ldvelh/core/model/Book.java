package com.arcreane.ldvelh.core.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.*;

/**
 * 
 */
@Entity
@Getter @Setter
public class Book {

 @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String title;
  /* private Set<Tags> types;
    private Map<Integer, Chapter> chapters;
    private int globalIndexValue;
*/
    public Book(){
        this("");
    }

    public Book(String title) {
        this.title = title;
       /* chapters = new HashMap<>();
        types = new HashSet<>();
        globalIndexValue = 0;
        Chapter.setGlobalIndex(0);*/
   }

    public void addChapter(Chapter chapter) {
       /* chapters.put(chapter.getId(), chapter);
        globalIndexValue = Chapter.getGlobalIndex();*/
    }

   /* @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", nb chapters= " + chapters.size() +
                '}';
    }*/

    public Chapter getChapterById(int parseInt) {
      //  return chapters.get(parseInt);
        return null;
    }


}