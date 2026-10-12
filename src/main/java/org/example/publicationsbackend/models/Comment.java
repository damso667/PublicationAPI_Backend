package org.example.publicationsbackend.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(
        name = "comments",
        indexes = {
                @Index(name = "idx_comments_publication", columnList = "publication_id"),
                @Index(name = "idx_comments_author", columnList = "author_id")
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_comment", updatable = false, nullable = false)
    private UUID id_comment;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    //ici cela reviens a dire que un commentaire appartient a une publication et qu'une publication peut avoir plusieurs commentaires
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "publication_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_comments_publication"))
    private Publication publication;

    //ici reviens a dire que un commentaire appartient a un utilisateur et qu'un utilisateur peut avoir plusieurs commentaires
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_comments_author"))
    private User author;

    @PrePersist
    void onCreate() {
        Instant now = Instant.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    void onUpdate() {
        this.updatedAt = Instant.now();
    }
}
