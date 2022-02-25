package com.alkemy.ong.dto;

import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.alkemy.ong.model.Slides;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationsAllDTO {

    @NotEmpty(message = "Name can't be empty")
    @NotNull(message = "Name can't be null")
    private String name;
    @NotEmpty(message = "Name can't be empty")
    @NotNull(message = "Name can't be null")
    private String images;
    private String addres;
    private int phone;
    @NotEmpty(message = "Name can't be empty")
    @NotNull(message = "Name can't be null")
    @Column(unique = true)
    private String email;
    @NotEmpty(message = "Name can't be empty")
    @NotNull(message = "Name can't be null")
    private String welcomeText;
    private String aboutUsText;
    private boolean isActive;
    private List<Slides> slides;

    private String facebookUrl;
    private String instagramUrl;
    private String linkedinUrl;
}
