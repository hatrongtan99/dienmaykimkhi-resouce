package com.hatrongtan99.app.entity;

import com.hatrongtan99.enumeration.media.MediaType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "media_table")
public class MediaEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String caption;

    private String filename;

    @Enumerated(EnumType.STRING)
    private MediaType mediaType;

    @Lob
    private byte[] data;

    private String url;
}
