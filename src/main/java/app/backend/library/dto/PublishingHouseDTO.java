package app.backend.library.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PublishingHouseDTO {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String address;
    @NotBlank
    private String contactNumber;
}
