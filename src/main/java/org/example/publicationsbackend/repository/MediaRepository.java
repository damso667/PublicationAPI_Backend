package org.example.publicationsbackend.repository;

import org.example.publicationsbackend.models.Media;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MediaRepository extends JpaRepository<Media, UUID> {
}
