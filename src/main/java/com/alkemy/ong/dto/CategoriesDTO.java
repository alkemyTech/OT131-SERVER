package com.alkemy.ong.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriesDTO {

    @NotBlank(message = "The name field cannot be empty")
    @NotNull(message = "Field name cannot be null")
    @Pattern(regexp = "[a-zA-Z\\sáéíóúÁÉÍÓÚÑñ]*", message = "The name can only contain letters")
    private String name;
    private String description;
    private String image;
}
