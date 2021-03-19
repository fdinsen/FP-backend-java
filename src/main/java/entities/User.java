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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.mindrot.jbcrypt.BCrypt;

@Entity
@Table(name = "users")
public class User implements Serializable {

        private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "u_id")
    private Long id;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "username", length = 25)
    private String username;
   
    @JoinTable(name = "personToBook", joinColumns = {
        @JoinColumn(name = "u_id", referencedColumnName = "u_id")}, inverseJoinColumns = {
        @JoinColumn(name = "b_id", referencedColumnName = "b_id")})
    @ManyToMany (cascade = CascadeType.PERSIST)
    private List<Book> booksOwned = new ArrayList<>();

    public User() {
    }


    public User(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Book> getBooksOwned() {
        return booksOwned;
    }

    public void setBooksOwned(List<Book> booksOwned) {
        this.booksOwned = booksOwned;
    }
    
    public void addBook(Book book ) {
        if(book != null) {
            booksOwned.add(book);
//            if(!(book.getUsers().contains(this)) ) {
//                book.addUser(this);
//            }
        }
    }
    
    

}
