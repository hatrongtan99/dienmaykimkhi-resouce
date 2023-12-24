package com.hatrongtan99.app.entity;

import com.hatrongtan99.enumeration.media.FileType;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Blob;

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
    private FileType fileType;

    private String mediaType;

    @Lob
    @Column(columnDefinition = "mediumblob default null")
    private byte[] data;

    private String url;
}
