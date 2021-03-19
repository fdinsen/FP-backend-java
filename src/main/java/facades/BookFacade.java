package facades;

import com.google.gson.Gson;
import dto.BookDTO;
import dto.UserDTO;
import entities.Author;
import entities.Book;
import entities.User;
import errorhandling.NotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import utils.HttpUtils;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class BookFacade {

    private static BookFacade instance;
    private static EntityManagerFactory emf;
    Gson GSON = new Gson();

    //Private Constructor to ensure Singleton
    private BookFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static BookFacade getBookFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new BookFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    //TODO Remove/Change this before use
    public long getBookCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long bookCount = (long) em.createQuery("SELECT COUNT(b) FROM Book b").getSingleResult();
            return bookCount;
        } finally {
            em.close();
        }
    }

    public void addBookToPerson(BookDTO bookdto, String username) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            User user;
            Author author;
            Book book;
            try {
                user = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                        .setParameter("username", username)
                        .getSingleResult();
            } catch (Exception e) {
                user = new User(username);
            }

//            try {
//                book = em.createQuery("SELECT b FROM Book b WHERE b.title =")
//            } catch (Exception e) {
//
//            }

            try {
                author = em.createQuery("SELECT a FROM Author a WHERE a.name = :name", Author.class)
                        .setParameter("name", bookdto.getAuthorName())
                        .getSingleResult();
                book = new Book(bookdto.getTitle(), author);
            } catch (Exception e) {
                author = new Author(bookdto.getAuthorName());
                book = new Book(bookdto.getTitle(), author);
            }

            user.addBook(book);
            em.persist(author);
            em.persist(user);
            em.persist(book);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public UserDTO getAllBooksOnPerson(String username) throws NotFoundException {
        EntityManager em = emf.createEntityManager();
        UserDTO user;
        try {
//            List l = em.createQuery("SELECT DISTINCT b FROM Book b JOIN b.users u JOIN b.author a WHERE u.username = :username", Book.class)
//                    .setParameter("username", username)
//                    .getResultList();
            List l = em.createQuery("SELECT u FROM User u JOIN u.booksOwned b JOIN b.author a WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .getResultList();
            if (l.size() > 0) {
                user = new UserDTO((User) l.get(0));
            } else {
                throw new NotFoundException();
            }
        } finally {
            em.close();
        }
        return user;
    }

}
