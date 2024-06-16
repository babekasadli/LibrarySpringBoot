package app.backend.library.service;

import app.backend.library.dto.BookDTO;
import app.backend.library.entity.Author;
import app.backend.library.entity.Book;
import app.backend.library.exceptions.ResourceNotFoundException;
import app.backend.library.repository.AuthorRepository;
import app.backend.library.repository.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    private static final String BOOK_NOT_FOUND_MESSAGE = "Book not found";

    private final AuthorService authorService;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.authorService = authorService;
    }

    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(this::convertToDto)
                .toList();
    }

    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(BOOK_NOT_FOUND_MESSAGE));
        return convertToDto(book);
    }

    public BookDTO createBook(BookDTO bookDto) {
        Book book = convertToEntity(bookDto);
        Book savedBook = bookRepository.save(book);
        return convertToDto(savedBook);
    }

    public BookDTO updateBook(Long id, BookDTO bookDto) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(BOOK_NOT_FOUND_MESSAGE));

        BeanUtils.copyProperties(bookDto, existingBook, "id");

        if (bookDto.getAuthor() != null) {
            Author author;
            if (bookDto.getAuthor().getId() != null) {
                author = authorRepository.findById(bookDto.getAuthor().getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Author not found"));
            } else {
                author = authorService.convertToEntity(bookDto.getAuthor());
                if (author.getId() == null) {
                    author = authorRepository.save(author);
                } else {
                    author = authorRepository.findById(author.getId())
                            .orElseThrow(() -> new ResourceNotFoundException("Author not found"));
                }
            }
            existingBook.setAuthor(author);
        }

        Book updatedBook = bookRepository.save(existingBook);
        return convertToDto(updatedBook);
    }

    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(BOOK_NOT_FOUND_MESSAGE));
        bookRepository.delete(book);
    }

    private BookDTO convertToDto(Book book) {
        BookDTO bookDto = new BookDTO();
        BeanUtils.copyProperties(book, bookDto);
        if (book.getAuthor() != null) {
            bookDto.setAuthor(authorService.convertToDto(book.getAuthor()));
        }
        return bookDto;
    }

    private Book convertToEntity(BookDTO bookDTO) {
        Book book = new Book();
        BeanUtils.copyProperties(bookDTO, book);
        if (bookDTO.getAuthor() != null) {
            Author author = authorService.convertToEntity(bookDTO.getAuthor());
            book.setAuthor(author);
        }
        return book;
    }
}
