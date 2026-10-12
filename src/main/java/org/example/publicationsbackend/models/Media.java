package org.example.publicationsbackend.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.publicationsbackend.enums.MediaType;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(
        name = "medias",
        indexes = {
                @Index(name = "idx_medias_publication", columnList = "publication_id")
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_media", updatable = false, nullable = false)
    private UUID id_media;


    @Column(name = "url", nullable = false, length = 500)
    private String url;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 20)
    private MediaType type;

    @Column(name = "mime_type", nullable = false, length = 100)
    private String mimeType;

    @Column(name = "size_bytes", nullable = false)
    private long sizeBytes;

    @Column(name = "display_order", nullable = false)
    private int displayOrder;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "publication_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_medias_publication"))
    private Publication publication;

    @PrePersist
    void onCreate() {
        this.createdAt = Instant.now();
    }
}
