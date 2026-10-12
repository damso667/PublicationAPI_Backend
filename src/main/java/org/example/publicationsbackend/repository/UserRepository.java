package org.example.publicationsbackend.repository;

import org.example.publicationsbackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

}
