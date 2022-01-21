package com.alkemy.ong.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "members")
public class Members implements Serializable{
    
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

    @Column(name = "timestamp")
    private Timestamp timestamp;

    @Column(name = "soft_delete")
    private Boolean softDelete;
}
