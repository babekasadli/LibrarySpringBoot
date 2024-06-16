package app.backend.library.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookDTO {
    private Long id;
    @NotBlank
    @Size(min = 2, max = 100)
    private String title;
    @NotBlank
    private String isbn;
    @NotNull
    private LocalDate publishedDate;
    @NotNull
    private int numberOfPages;
    @NotBlank
    private String genre;
    @NotNull
    private AuthorDTO author;
    @NotNull
    private PublishingHouseDTO publishingHouse;
}
