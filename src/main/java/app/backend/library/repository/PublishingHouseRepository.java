package app.backend.library.repository;

import app.backend.library.entity.PublishingHouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublishingHouseRepository extends JpaRepository<PublishingHouse, Long> {
}
