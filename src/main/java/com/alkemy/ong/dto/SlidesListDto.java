package com.alkemy.ong.dto;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SlidesListDto {

    private String imageUrl;
    @Column(unique = true)
    private Integer order;

}
