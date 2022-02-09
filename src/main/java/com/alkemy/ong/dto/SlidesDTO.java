package com.alkemy.ong.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SlidesDTO {

    @NotBlank(message = "An image encoded in base64 must be specified.")
    private String image64;

    @NotBlank(message = "A text for the slide must be provided.")
    private String text;

    private Integer order;

    @NotNull(message = "The slide needs the id of the organization that is related to.")
    private Long organizationId;
}
