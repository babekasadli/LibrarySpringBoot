package app.backend.library.service;

import app.backend.library.dto.AuthorDTO;
import app.backend.library.entity.Author;
import app.backend.library.exceptions.ResourceNotFoundException;
import app.backend.library.repository.AuthorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private static final String AUTHOR_NOT_FOUND = "Author not found";

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<AuthorDTO> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream()
                .map(this::convertToDto)
                .toList();
    }

    public AuthorDTO getAuthorById(Long id) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        Author author = optionalAuthor.orElseThrow(() -> new ResourceNotFoundException(AUTHOR_NOT_FOUND));
        return convertToDto(author);
    }

    public AuthorDTO createAuthor(AuthorDTO authorDto) {
        Author author = convertToEntity(authorDto);
        Author savedAuthor = authorRepository.save(author);
        return convertToDto(savedAuthor);
    }

    public AuthorDTO updateAuthor(Long id, AuthorDTO authorDto) {
        Author existingAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(AUTHOR_NOT_FOUND));
        BeanUtils.copyProperties(authorDto, existingAuthor, "id");
        Author updatedAuthor = authorRepository.save(existingAuthor);
        return convertToDto(updatedAuthor);
    }

    public void deleteAuthor(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(AUTHOR_NOT_FOUND));
        authorRepository.delete(author);
    }

    Author convertToEntity(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setId(authorDTO.getId());
        author.setName(authorDTO.getName());
        author.setBiography(authorDTO.getBiography());
        author.setNationality(authorDTO.getNationality());
        return author;
    }

    AuthorDTO convertToDto(Author author) {
        AuthorDTO authorDto = new AuthorDTO();
        BeanUtils.copyProperties(author, authorDto);
        return authorDto;
    }
}
