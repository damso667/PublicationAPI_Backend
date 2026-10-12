package org.example.publicationsbackend.repository;

import org.example.publicationsbackend.models.Publication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PublicationRepository extends JpaRepository<Publication, UUID> {
}
