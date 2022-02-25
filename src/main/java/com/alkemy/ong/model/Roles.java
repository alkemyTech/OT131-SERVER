package com.alkemy.ong.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.alkemy.ong.util.RoleName;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.Nullable;

import lombok.Data;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)

public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private RoleName name;
    @Nullable
    private String description;
    @OneToMany
    private List<Users> users;

    public Roles() {
    }

    public Roles(@NotNull RoleName name, String description, List<Users> users) {
        this.name = name;
        this.description = description;
        this.users = users;
    }

    public Roles(RoleName name, @Nullable String description) {
        this.name = name;
        this.description = description;
    }
}
