package com.alkemy.ong.model;

import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Slides {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_slide")
    private Long id;

    @NotNull
    @Column(name = "image_url")
    private String imageUrl;

    @NotNull
    private String text;

    @NotNull
    @Column(name = "order_number")
    private Integer order;

    @NotNull
    @Column(name = "organization_id")
    private Long organizationId;

    @CreatedDate
    @Column(name = "date_created")
    private LocalDate dateCreated;

    @LastModifiedDate
    @Column(name = "date_modified")
    private LocalDate dateModified;

    @Column(name = "is_active")
    private boolean isActive = Boolean.TRUE;

}
