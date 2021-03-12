package utils;

import entities.Author;
import entities.Book;
import entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class SetupTestUsers {
  public static void main(String[] args) {

    EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
    EntityManager em = emf.createEntityManager();
    
    // IMPORTAAAAAAAAAANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // This breaks one of the MOST fundamental security rules in that it ships with default users and passwords
    // CHANGE the three passwords below, before you uncomment and execute the code below
    // Also, either delete this file, when users are created or rename and add to .gitignore
    // Whatever you do DO NOT COMMIT and PUSH with the real passwords

    Author author = new Author("Dickens");
    User user = new User("frederik");
    User user2 = new User("peter");
    User user3 = new User("karen");
    Book book = new Book("Book1", author);
    Book book2 = new Book("book 2", author);
    Book book3 = new Book("Book third", author);

    user.addBook(book);
    user.addBook(book2);
    user.addBook(book3);
    user2.addBook(book);
    user2.addBook(book2);
    user3.addBook(book);
    
    em.getTransaction().begin();
    em.persist(author);
    em.persist(user);
    em.persist(user2);
    em.persist(user3);
    em.persist(book);
    em.persist(book2);
    em.persist(book3);
    em.getTransaction().commit();
    System.out.println("Created TEST Users");
   
  }

}
