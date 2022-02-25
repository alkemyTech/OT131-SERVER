package com.alkemy.ong.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Table(name = "organizations")
@EntityListeners(AuditingEntityListener.class)
public class Organizations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull(message = "Name can't be null")
    private String name;
    @NotNull
    private String images;
    private String addres;
    private int phone;
    @NotNull
    private String email;
    @NotNull
    private String welcomeText;
    @Column(length = 5000)
    @Size(max = 5000)
    private String aboutUsText;
    @Column(name = "modified_date")
    @UpdateTimestamp
    private LocalDate modifiedDate;
    @Column(name = "created_date")
    @CreationTimestamp
    private LocalDate createdDate;
    @OneToMany(mappedBy = "organization")
    private List<Slides> slide;
    private Boolean isActive = Boolean.TRUE;

    private String facebookUrl;
    private String instagramUrl;
    private String linkedinUrl;

}
