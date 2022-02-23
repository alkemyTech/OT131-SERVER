package com.alkemy.ong.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivitiesDTO {

    private Long id;

    @NotBlank(message = "The name must be a valid value.")
    private String name;

    @NotBlank(message = "The content must be a valid value.")
    private String content;

    @NotBlank(message = "The image must be a valid value.")
    private String image;

    private String createdDate;

    private String modifiedDate;
}
