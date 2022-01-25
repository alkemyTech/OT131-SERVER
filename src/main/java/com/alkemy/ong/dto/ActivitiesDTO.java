package com.alkemy.ong.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivitiesDTO {

    private Long id;

    @NotNull(message = "The name must be not null.")
    @NotEmpty(message = "The name must be not empty.")
    private String name;

    @NotNull(message = "The content must be not null.")
    @NotEmpty(message = "The content must be not empty.")
    private String content;

    @NotNull(message = "The image must be not null.")
    @NotEmpty(message = "The image must be not empty.")
    private String image;

    private String createdDate;

    private String modifiedDate;
}
