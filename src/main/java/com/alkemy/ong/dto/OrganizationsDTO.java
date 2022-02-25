package com.alkemy.ong.dto;

import java.util.List;

import com.alkemy.ong.model.Slides;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationsDTO {

    private String name;
    private String images;
    private String addres;
    private int phone;
    private List<Slides> slides;
    private String facebookUrl;
    private String instagramUrl;
    private String linkedinUrl;
}
