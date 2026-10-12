package org.example.publicationsbackend.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(
        name = "follows",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_follows_pair", columnNames = {"follower_id", "following_id"})
        },
        indexes = {
                @Index(name = "idx_follows_follower", columnList = "follower_id"),
                @Index(name = "idx_follows_following", columnList = "following_id")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_follow", updatable = false, nullable = false)
    private UUID id_follow;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "follower_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_follows_follower"))
    private User follower;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "following_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_follows_following"))
    private User following;

    @PrePersist
    void onCreate() {
        this.createdAt = Instant.now();
    }
}
