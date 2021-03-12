/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Book;

/**
 *
 * @author gamma
 */
public class BookDTO {
    private String title;
    private AuthorDTO author;

    public BookDTO() {
    } 
    
    public BookDTO(Book book) {
        title = book.getTitle();
        author = new AuthorDTO(book.getAuthor());
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }

public String getAuthorName() {
    return author.getName();
}    
}
