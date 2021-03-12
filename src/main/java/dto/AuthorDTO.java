/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Author;

/**
 *
 * @author gamma
 */
class AuthorDTO {
    long id;
    String name;

    public AuthorDTO() {
    }
    
    public AuthorDTO(Author author) {
        id = author.getId();
        name = author.getName();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
