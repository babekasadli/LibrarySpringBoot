package app.backend.library.util;

import app.backend.library.entity.Author;
import app.backend.library.entity.Book;
import app.backend.library.entity.PublishingHouse;
import app.backend.library.entity.User;
import app.backend.library.repository.AuthorRepository;
import app.backend.library.repository.BookRepository;
import app.backend.library.repository.PublishingHouseRepository;
import app.backend.library.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Set;

@Component
public class DemoDataLoader implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublishingHouseRepository publishingHouseRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DemoDataLoader(AuthorRepository authorRepository, BookRepository bookRepository,
                          PublishingHouseRepository publishingHouseRepository, UserRepository userRepository,
                          PasswordEncoder passwordEncoder) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publishingHouseRepository = publishingHouseRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        if (authorRepository.count() == 0 && bookRepository.count() == 0 && publishingHouseRepository.count() == 0) {
            loadDemoData();
        }
    }

    private void loadDemoData() {
        PublishingHouse ph1 = new PublishingHouse();
        ph1.setName("Penguin Random House");
        ph1.setAddress("New York, USA");
        ph1.setContactNumber("123-456-7890");
        ph1 = publishingHouseRepository.save(ph1);

        PublishingHouse ph2 = new PublishingHouse();
        ph2.setName("HarperCollins");
        ph2.setAddress("London, UK");
        ph2.setContactNumber("098-765-4321");
        ph2 = publishingHouseRepository.save(ph2);

        PublishingHouse ph3 = new PublishingHouse();
        ph3.setName("Simon & Schuster");
        ph3.setAddress("New York, USA");
        ph3.setContactNumber("555-555-5555");
        ph3 = publishingHouseRepository.save(ph3);

        Author author1 = new Author();
        author1.setName("George Orwell");
        author1.setBiography("George Orwell was an English novelist, essayist, journalist and critic.");
        author1.setNationality("British");
        author1 = authorRepository.save(author1);

        Author author2 = new Author();
        author2.setName("J.K. Rowling");
        author2.setBiography("J.K. Rowling is a British author, best known for the Harry Potter series.");
        author2.setNationality("British");
        author2 = authorRepository.save(author2);

        Author author3 = new Author();
        author3.setName("Stephen King");
        author3.setBiography("Stephen King is an American author of horror, supernatural fiction, suspense, crime, science-fiction, and fantasy novels.");
        author3.setNationality("American");
        author3 = authorRepository.save(author3);

        Book book1 = new Book();
        book1.setTitle("1984");
        book1.setIsbn("123-456-789");
        book1.setPublishedDate(LocalDate.of(1949, 6, 8));
        book1.setNumberOfPages(328);
        book1.setGenre("Dystopian");
        book1.setAuthor(author1);
        book1.setPublishingHouse(ph1);
        bookRepository.save(book1);

        Book book2 = new Book();
        book2.setTitle("Harry Potter and the Sorcerer's Stone");
        book2.setIsbn("987-654-321");
        book2.setPublishedDate(LocalDate.of(1997, 6, 26));
        book2.setNumberOfPages(223);
        book2.setGenre("Fantasy");
        book2.setAuthor(author2);
        book2.setPublishingHouse(ph2);
        bookRepository.save(book2);

        Book book3 = new Book();
        book3.setTitle("The Shining");
        book3.setIsbn("456-789-123");
        book3.setPublishedDate(LocalDate.of(1977, 1, 28));
        book3.setNumberOfPages(447);
        book3.setGenre("Horror");
        book3.setAuthor(author3);
        book3.setPublishingHouse(ph3);
        bookRepository.save(book3);

        Book book4 = new Book();
        book4.setTitle("Animal Farm");
        book4.setIsbn("654-321-987");
        book4.setPublishedDate(LocalDate.of(1945, 8, 17));
        book4.setNumberOfPages(112);
        book4.setGenre("Political satire");
        book4.setAuthor(author1);
        book4.setPublishingHouse(ph1);
        bookRepository.save(book4);

        Book book5 = new Book();
        book5.setTitle("Harry Potter and the Chamber of Secrets");
        book5.setIsbn("321-987-654");
        book5.setPublishedDate(LocalDate.of(1998, 7, 2));
        book5.setNumberOfPages(251);
        book5.setGenre("Fantasy");
        book5.setAuthor(author2);
        book5.setPublishingHouse(ph2);
        bookRepository.save(book5);

        Book book6 = new Book();
        book6.setTitle("Carrie");
        book6.setIsbn("789-123-456");
        book6.setPublishedDate(LocalDate.of(1974, 4, 5));
        book6.setNumberOfPages(199);
        book6.setGenre("Horror");
        book6.setAuthor(author3);
        book6.setPublishingHouse(ph3);
        bookRepository.save(book6);

        User user1 = new User();
        user1.setUsername("admin");
        user1.setPassword(passwordEncoder.encode("admin123"));
        user1.setRoles(Set.of("ROLE_ADMIN"));
        userRepository.save(user1);

        User user2 = new User();
        user2.setUsername("user");
        user2.setPassword(passwordEncoder.encode("user123"));
        user2.setRoles(Set.of("ROLE_USER"));
        userRepository.save(user2);

    }
}
