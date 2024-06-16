package app.backend.library.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuthorDTO {
    private Long id;
    @NotBlank
    @Size(min = 2, max = 100)
    private String name;
    @NotBlank
    private String biography;
    @NotBlank
    @Size(min = 2, max = 100)
    private String nationality;
}
