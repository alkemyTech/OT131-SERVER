package com.alkemy.ong.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.alkemy.ong.utility.RoleName;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @Enumerated(EnumType.STRING)
    private RoleName name;
    @Nullable
    private String description;
    @OneToMany
    private List <User> users;

    public Role() {
    }

    public Role(RoleName name, String description, List <User> users) {
        this.name = name;
        this.description = description;
        this.users = users;
    }

    public String getDescription() {
        return description;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List <User> getUser() {
        return users;
    }

    public void setUser(List <User> users) {
        this.users = users;
    }

}