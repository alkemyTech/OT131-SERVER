package com.alkemy.ong.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriesDTO {

    @NotBlank(message = "The name must be a valid value.")
    private String name;
    private String description;
    private String image;
}
