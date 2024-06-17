package app.backend.library.util;

import app.backend.library.entity.Author;
import app.backend.library.entity.Book;
import app.backend.library.entity.PublishingHouse;
import app.backend.library.entity.User;
import app.backend.library.repository.AuthorRepository;
import app.backend.library.repository.BookRepository;
import app.backend.library.repository.PublishingHouseRepository;
import app.backend.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Set;

@Component
@EnableConfigurationProperties(DemoDataProperties.class)
public class DemoDataLoader implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublishingHouseRepository publishingHouseRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final DemoDataProperties demoDataProperties;

    @Autowired
    public DemoDataLoader(AuthorRepository authorRepository, BookRepository bookRepository,
                          PublishingHouseRepository publishingHouseRepository, UserRepository userRepository,
                          PasswordEncoder passwordEncoder, DemoDataProperties demoDataProperties) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publishingHouseRepository = publishingHouseRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.demoDataProperties = demoDataProperties;
    }

    @Transactional
    @Override
    public void run(String... args) {
        if (authorRepository.count() == 0 && bookRepository.count() == 0 && publishingHouseRepository.count() == 0) {
            loadDemoData();
        }
    }

    private void loadDemoData() {
        PublishingHouse ph1 = new PublishingHouse();
        ph1.setName("Kitabxana Nəşriyyatı");
        ph1.setAddress("Bakı, Azərbaycan");
        ph1.setContactNumber("+994129563322");
        ph1 = publishingHouseRepository.save(ph1);

        PublishingHouse ph2 = new PublishingHouse();
        ph2.setName("Qanun Nəşriyyatı");
        ph2.setAddress("Bakı, Azərbaycan");
        ph2.setContactNumber("+994559511520");
        ph2 = publishingHouseRepository.save(ph2);

        Author author1 = new Author();
        author1.setName("Əhməd Cəmil");
        author1.setBiography("Əhməd Cəmil Azərbaycan yazıçısı və şairidir.");
        author1.setNationality("Azərbaycanlı");
        author1 = authorRepository.save(author1);

        Author author2 = new Author();
        author2.setName("Elçin");
        author2.setBiography("Elçin ən sevilən Azərbaycan yazıçılarından biridir.");
        author2.setNationality("Azərbaycanlı");
        author2 = authorRepository.save(author2);

        Author author3 = new Author();
        author3.setName("Gabriel Garcia Marquez");
        author3.setBiography("Gabriel Garcia Marquez was a Colombian novelist, short-story writer, screenwriter, and journalist.");
        author3.setNationality("Colombian");
        author3 = authorRepository.save(author3);

        Author author4 = new Author();
        author4.setName("Haruki Murakami");
        author4.setBiography("Haruki Murakami is a Japanese writer. His books and stories have been bestsellers in Japan and internationally.");
        author4.setNationality("Japanese");
        author4 = authorRepository.save(author4);

        Author author5 = new Author();
        author5.setName("Elena Ferrante");
        author5.setBiography("Elena Ferrante is the pseudonymous Italian novelist whose Neapolitan Quartet series of novels brought her international acclaim.");
        author5.setNationality("Italian");
        author5 = authorRepository.save(author5);

        Book book1 = new Book();
        book1.setTitle("One Hundred Years of Solitude");
        book1.setIsbn("978-0-140-28322-2");
        book1.setPublishedDate(LocalDate.of(1967, 5, 30));
        book1.setNumberOfPages(417);
        book1.setGenre("Magical Realism");
        book1.setAuthor(author3);
        book1.setPublishingHouse(ph1);
        bookRepository.save(book1);

        Book book2 = new Book();
        book2.setTitle("Norwegian Wood");
        book2.setIsbn("978-0-307-59373-9");
        book2.setPublishedDate(LocalDate.of(1987, 8, 28));
        book2.setNumberOfPages(296);
        book2.setGenre("Literary Fiction");
        book2.setAuthor(author4);
        book2.setPublishingHouse(ph2);
        bookRepository.save(book2);

        Book book3 = new Book();
        book3.setTitle("My Brilliant Friend");
        book3.setIsbn("978-1-60-945078-6");
        book3.setPublishedDate(LocalDate.of(2011, 9, 25));
        book3.setNumberOfPages(331);
        book3.setGenre("Fiction");
        book3.setAuthor(author5);
        book3.setPublishingHouse(ph1);
        bookRepository.save(book3);

        Book book4 = new Book();
        book4.setTitle("Ana");
        book4.setIsbn("978-9952-451-30-0");
        book4.setPublishedDate(LocalDate.of(1922, 1, 1));
        book4.setNumberOfPages(200);
        book4.setGenre("Roman");
        book4.setAuthor(author1);
        book4.setPublishingHouse(ph1);
        bookRepository.save(book4);

        Book book5 = new Book();
        book5.setTitle("Xalq üçün ədəbi tədris");
        book5.setIsbn("978-9952-451-31-7");
        book5.setPublishedDate(LocalDate.of(1912, 1, 1));
        book5.setNumberOfPages(180);
        book5.setGenre("Sənət");
        book5.setAuthor(author1);
        book5.setPublishingHouse(ph2);
        bookRepository.save(book5);

        Book book6 = new Book();
        book6.setTitle("Qanun");
        book6.setIsbn("978-9952-451-32-4");
        book6.setPublishedDate(LocalDate.of(1900, 1, 1));
        book6.setNumberOfPages(250);
        book6.setGenre("Roman");
        book6.setAuthor(author2);
        book6.setPublishingHouse(ph1);
        bookRepository.save(book6);

        User user1 = new User();
        user1.setUsername("admin");
        user1.setPassword(passwordEncoder.encode(demoDataProperties.getAdminPassword()));
        user1.setRoles(Set.of("ROLE_ADMIN"));
        userRepository.save(user1);

        User user2 = new User();
        user2.setUsername("user");
        user2.setPassword(passwordEncoder.encode(demoDataProperties.getAdminPassword()));
        user2.setRoles(Set.of("ROLE_USER"));
        userRepository.save(user2);
    }
}
