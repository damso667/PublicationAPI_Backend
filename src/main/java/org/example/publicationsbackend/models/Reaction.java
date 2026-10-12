package org.example.publicationsbackend.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(
        name = "reactions",
        indexes = {
                @Index(name = "idx_reactions_user", columnList = "user_id"),
                @Index(name = "idx_reactions_publication", columnList = "publication_id"),
                @Index(name = "idx_reactions_comment", columnList = "comment_id")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_reaction", updatable = false, nullable = false)
    private UUID id_reation;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_reactions_user"))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publication_id",
            foreignKey = @ForeignKey(name = "fk_reactions_publication"))
    private Publication publication;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id",
            foreignKey = @ForeignKey(name = "fk_reactions_comment"))
    private Comment comment;

    @PrePersist
    void onCreate() {
        this.createdAt = Instant.now();
    }
}
