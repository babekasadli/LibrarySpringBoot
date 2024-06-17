package app.backend.library.controller;

import app.backend.library.dto.PublishingHouseDTO;
import app.backend.library.exceptions.ResourceNotFoundException;
import app.backend.library.service.PublishingHouseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publishing-houses")
@Validated
@Tag(name = "Publishing Houses", description = "APIs related to managing publishing houses")
public class PublishingHouseController {

    private final PublishingHouseService publishingHouseService;

    @Autowired
    public PublishingHouseController(PublishingHouseService publishingHouseService) {
        this.publishingHouseService = publishingHouseService;
    }

    @Operation(summary = "Get all publishing houses", description = "Get all publishing houses")
    @GetMapping
    public List<PublishingHouseDTO> getAllPublishingHouses() {
        return publishingHouseService.getAllPublishingHouses();
    }

    @Operation(summary = "Get a publishing house by ID", description = "Provide an ID to look up a specific publishing house")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved publishing house"),
            @ApiResponse(responseCode = "404", description = "Publishing house not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PublishingHouseDTO> getPublishingHouseById(@PathVariable Long id) {
        PublishingHouseDTO publishingHouse = publishingHouseService.getPublishingHouseById(id);
        return ResponseEntity.ok(publishingHouse);
    }

    @Operation(summary = "Add a new publishing house")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Publishing house created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Publishing house already exists")
    })
    @PostMapping
    public ResponseEntity<PublishingHouseDTO> createPublishingHouse(@Valid @RequestBody PublishingHouseDTO publishingHouseDto) {
        PublishingHouseDTO createdPublishingHouse = publishingHouseService.createPublishingHouse(publishingHouseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPublishingHouse);
    }

    @Operation(summary = "Update an existing publishing house", description = "Update publishing house details by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated publishing house"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Publishing house not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PublishingHouseDTO> updatePublishingHouse(@PathVariable Long id, @Valid @RequestBody PublishingHouseDTO publishingHouseDto) {
        PublishingHouseDTO updatedPublishingHouse = publishingHouseService.updatePublishingHouse(id, publishingHouseDto);
        return ResponseEntity.ok(updatedPublishingHouse);
    }

    @Operation(summary = "Delete a publishing house", description = "Delete a publishing house by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Publishing house deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Publishing house not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublishingHouse(@PathVariable Long id) {
        publishingHouseService.deletePublishingHouse(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
