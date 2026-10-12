package org.example.publicationsbackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.publicationsbackend.enums.PublicationVisibility;

import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "Publications",
        indexes = {
           @Index(name = "idx_publications_author", columnList = "author_id"),
           @Index(name = "idx_publications_created_at", columnList = "created_at")
        }
)
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id_publication;

    @Column(name = "text_content", columnDefinition = "TEXT")
    private String textContent;

    @Enumerated(EnumType.STRING)
    @Column(name = "visibility", nullable = false, length = 20)
    private PublicationVisibility visibility;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @PrePersist
    void onCreate() {
        Instant now = Instant.now();
        this.createdAt = now;
        this.updatedAt = now;
        if (this.visibility == null) this.visibility = PublicationVisibility.PUBLIC;
    }

    @PreUpdate
    void onUpdate() {
        this.updatedAt = Instant.now();
    }
}
