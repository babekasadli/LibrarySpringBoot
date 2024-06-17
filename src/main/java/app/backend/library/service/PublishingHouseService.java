package app.backend.library.service;

import app.backend.library.dto.PublishingHouseDTO;
import app.backend.library.entity.PublishingHouse;
import app.backend.library.exceptions.ResourceNotFoundException;
import app.backend.library.repository.PublishingHouseRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PublishingHouseService {
    private final PublishingHouseRepository publishingHouseRepository;
    private static final String PH_NOT_FOUND = "Publishing house not found";

    @Autowired
    public PublishingHouseService(PublishingHouseRepository publishingHouseRepository) {
        this.publishingHouseRepository = publishingHouseRepository;
    }

    public List<PublishingHouseDTO> getAllPublishingHouses() {
        List<PublishingHouse> publishingHouses = publishingHouseRepository.findAll();
        return publishingHouses.stream()
                .map(this::convertToDto)
                .toList();
    }

    public PublishingHouseDTO getPublishingHouseById(Long id) {
        PublishingHouse publishingHouse = publishingHouseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(PH_NOT_FOUND));
        return convertToDto(publishingHouse);
    }

    public PublishingHouseDTO createPublishingHouse(PublishingHouseDTO publishingHouseDto) {
        PublishingHouse publishingHouse = convertToEntity(publishingHouseDto);
        PublishingHouse savedPublishingHouse = publishingHouseRepository.save(publishingHouse);
        return convertToDto(savedPublishingHouse);
    }

    public PublishingHouseDTO updatePublishingHouse(Long id, PublishingHouseDTO publishingHouseDto) {
        PublishingHouse existingPublishingHouse = publishingHouseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(PH_NOT_FOUND));
        BeanUtils.copyProperties(publishingHouseDto, existingPublishingHouse, "id");
        PublishingHouse updatedPublishingHouse = publishingHouseRepository.save(existingPublishingHouse);
        return convertToDto(updatedPublishingHouse);
    }

    public void deletePublishingHouse(Long id) {
        PublishingHouse publishingHouse = publishingHouseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(PH_NOT_FOUND));
        publishingHouseRepository.delete(publishingHouse);
    }

    private PublishingHouseDTO convertToDto(PublishingHouse publishingHouse) {
        PublishingHouseDTO publishingHouseDto = new PublishingHouseDTO();
        BeanUtils.copyProperties(publishingHouse, publishingHouseDto);
        return publishingHouseDto;
    }

    private PublishingHouse convertToEntity(PublishingHouseDTO publishingHouseDto) {
        PublishingHouse publishingHouse = new PublishingHouse();
        BeanUtils.copyProperties(publishingHouseDto, publishingHouse);
        return publishingHouse;
    }
}
