package app.backend.library.controller;

import app.backend.library.dto.PublishingHouseDTO;
import app.backend.library.service.PublishingHouseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publishingHouses")
@Validated
public class PublishingHouseController {
    private final PublishingHouseService publishingHouseService;

    public PublishingHouseController(PublishingHouseService publishingHouseService) {
        this.publishingHouseService = publishingHouseService;
    }

    @GetMapping
    public List<PublishingHouseDTO> getAllPublishingHouses() {
        return publishingHouseService.getAllPublishingHouses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublishingHouseDTO> getPublishingHouseById(@PathVariable Long id) {
        PublishingHouseDTO publishingHouse = publishingHouseService.getPublishingHouseById(id);
        return ResponseEntity.ok(publishingHouse);
    }
    @PostMapping
    public ResponseEntity<PublishingHouseDTO> createPublishingHouse(@Valid @RequestBody PublishingHouseDTO publishingHouseDto) {
        PublishingHouseDTO createdPublishingHouse = publishingHouseService.createPublishingHouse(publishingHouseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPublishingHouse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublishingHouseDTO> updatePublishingHouse(@PathVariable Long id, @RequestBody PublishingHouseDTO publishingHouseDto) {
        PublishingHouseDTO updatedPublishingHouse = publishingHouseService.updatePublishingHouse(id, publishingHouseDto);
        return ResponseEntity.ok(updatedPublishingHouse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublishingHouse(@PathVariable Long id) {
        publishingHouseService.deletePublishingHouse(id);
        return ResponseEntity.noContent().build();
    }
}
