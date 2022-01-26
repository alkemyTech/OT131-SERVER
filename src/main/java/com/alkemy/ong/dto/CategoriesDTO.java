package com.alkemy.ong.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriesDTO {

    private String name;
    private String description;
    private String image;
}
