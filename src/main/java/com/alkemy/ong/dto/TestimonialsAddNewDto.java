package com.alkemy.ong.dto;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestimonialsAddNewDto implements Serializable {

    @NotNull(message = "Name can't be null")
    @NotEmpty(message = "Name cannot be empty")
    private String name;

    private String image;

    @NotNull(message = "Content can't be null")
    @NotEmpty(message = "Content cannot be empty")
    private String content;

}
