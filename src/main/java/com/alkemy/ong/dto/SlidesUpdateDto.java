package com.alkemy.ong.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SlidesUpdateDto {

    private String imageUrl;
    private String text;
    private Integer order;
    private Long organizationId;
    private LocalDate dateModifed;
}