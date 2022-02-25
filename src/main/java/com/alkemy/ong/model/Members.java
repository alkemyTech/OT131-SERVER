package com.alkemy.ong.model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "members")
public class Members implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_member")
    private Long idMember;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "facebook_url")
    private String facebookUrl;

    @Column(name = "instagram_url")
    private String instagramUrl;

    @Column(name = "linkedin_url")
    private String linkedinUrl;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "description")
    private String description;

    @CreationTimestamp
    @Column(name = "date_created")
    private LocalDate dateCreated;

    @UpdateTimestamp
    @Column(name = "date_modified")
    private LocalDate dateModified;

    @Column(name = "is_active")
    private Boolean isActive = Boolean.TRUE;

    public Members(String name, String facebookUrl, String instagramUrl, String linkedinUrl, String image, String description) {
        this.name = name;
        this.facebookUrl = facebookUrl;
        this.instagramUrl = instagramUrl;
        this.linkedinUrl = linkedinUrl;
        this.image = image;
        this.description = description;
    }

}
