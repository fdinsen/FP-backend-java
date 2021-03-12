/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gamma
 */
public class UserDTO {
    long id;
    String username;
    List<BookDTO> books;

    public UserDTO(long id, String username) {
        this.id = id;
        this.username = username;
        this.books = new ArrayList();
    }

    public UserDTO() {
        books = new ArrayList();
    }
    
    public UserDTO(User user) {
        this.username = user.getUsername();
        books = new ArrayList();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookDTO> books) {
        this.books = books;
    }
    
    public void addBook(BookDTO book) {
        this.books.add(book);
    }
    
    
}
