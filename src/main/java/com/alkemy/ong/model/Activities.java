package com.alkemy.ong.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "activities")
public class Activities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activities_id")
    private Long id;

    @NotNull
    private String name;
    @Column(length = 7000)
    @NotNull
    private String content;

    @NotNull
    private String image;

    @Column(name = "created_date", updatable = false, nullable = false)
    @CreatedDate
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate modifiedDate;

    @Column(name = "deleted_date")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate deletedDate;

    @Column(name = "is_active")
    private boolean isActive = Boolean.TRUE;

    public Activities(@NotNull String name, @NotNull String content, @NotNull String image, LocalDate createdDate,
            boolean isActive) {
        super();
        this.name = name;
        this.content = content;
        this.image = image;
        this.createdDate = createdDate;
        this.isActive = isActive;
    }

}
