package org.example.publicationsbackend.models;

import jakarta.persistence.*;
import lombok.*;
import org.example.publicationsbackend.enums.OtpPurpose;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(
        name = "otp_codes",
        indexes = {
                @Index(name = "idx_otp_codes_user", columnList = "user_id"),
                @Index(name = "idx_otp_codes_code", columnList = "code")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OtpCode {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_otp_codes_user"))
    private User user;

    @Column(name = "code", nullable = false, length = 6)
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(name = "purpose", nullable = false, length = 30)
    private OtpPurpose purpose;

    @Column(name = "expires_at", nullable = false)
    private Instant expiresAt;

    @Column(name = "used", nullable = false)
    private boolean used;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @PrePersist
    void onCreate() {
        this.createdAt = Instant.now();
        this.used = false;
    }
}
