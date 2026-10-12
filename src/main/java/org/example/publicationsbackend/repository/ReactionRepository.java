package org.example.publicationsbackend.repository;

import org.example.publicationsbackend.models.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReactionRepository extends JpaRepository<Reaction, UUID> {
}
