package com.alkemy.ong.dto;

import javax.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewMemberDTO {

    @NotBlank(message = "Name cannot be null or empty")
    @Pattern(regexp = "[a-zA-Z\\sáéíóúÁÉÍÓÚÑñ]*", message = "The name can only contain letters")
    private String name;

    private String facebookUrl;
    private String instagramUrl;
    private String linkedinUrl;

    @NotNull(message = "Image cannot be null or empty")
    private String image;

    private String description;

}
