package com.alkemy.ong.dto;

import com.alkemy.ong.model.Organizations;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SlidesResponseDTO {

    private Long id;
    private String imageUrl;
    private String text;
    private Integer order;
    private Organizations organization;
}
