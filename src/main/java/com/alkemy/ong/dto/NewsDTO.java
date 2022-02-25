package com.alkemy.ong.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewsDTO {

    @NotBlank(message = "The name must be a valid value.")
    private String name;
    @NotBlank(message = "The content must be a valid value.")
    private String content;
    @NotBlank(message = "The image must be a valid value.")
    private String image;
    @NotNull(message = "The News must have a valid category id")
    private Long idCategory;

    private LocalDate dateCreated;
}
