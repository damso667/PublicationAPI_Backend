package org.example.publicationsbackend.repository;

import org.example.publicationsbackend.models.OtpCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OtpCodeRepository extends JpaRepository<OtpCode, UUID> {
}
