package app.backend.library.controller;

import app.backend.library.dto.PublishingHouseDTO;
import app.backend.library.service.PublishingHouseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publishingHouses")
@Validated
@Tag(name = "Publishing Houses", description = "APIs related to managing publishing houses")
public class PublishingHouseController {

    private final PublishingHouseService publishingHouseService;

    public PublishingHouseController(PublishingHouseService publishingHouseService) {
        this.publishingHouseService = publishingHouseService;
    }

    @Operation(summary = "Get all publishing houses", description = "Retrieve all publishing houses")
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
        if (publishingHouse != null) {
            return ResponseEntity.ok(publishingHouse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Add a new publishing house", description = "Create a new publishing house with provided details")
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

    @Operation(summary = "Update an existing publishing house", description = "Update details of an existing publishing house")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Publishing house updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Publishing house not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PublishingHouseDTO> updatePublishingHouse(@PathVariable Long id, @Valid @RequestBody PublishingHouseDTO publishingHouseDto) {
        PublishingHouseDTO updatedPublishingHouse = publishingHouseService.updatePublishingHouse(id, publishingHouseDto);
        if (updatedPublishingHouse != null) {
            return ResponseEntity.ok(updatedPublishingHouse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete a publishing house", description = "Remove a publishing house from the records")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Publishing house deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Publishing house not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublishingHouse(@PathVariable Long id) {
        boolean deleted = publishingHouseService.deletePublishingHouse(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
