package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@NamedQuery(name = "Book.deleteAllRows", query = "DELETE from Book")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "b_id")
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "title")
    private String title;

    @JoinColumn(name = "a_id", referencedColumnName ="a_id")
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    private Author author;

//    @ManyToMany(mappedBy = "booksOwned", cascade = CascadeType.PERSIST)
//    List<User> users = new ArrayList();

    public Book() {
    }

    public Book(String title, Author author) {
        this.title = title;
        this.author = author;
        if(author != null && !author.getBooks().contains(this)) {
            author.addBook(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

//    public List<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(List<User> users) {
//        this.users = users;
//    }
//
//    public void addUser(User user) {
//        if (user != null) {
//            users.add(user);
//            if(!(user.getBooksOwned().contains(this)) ) {
//                user.addBook(this);
//            }
//        }
//    }

}
