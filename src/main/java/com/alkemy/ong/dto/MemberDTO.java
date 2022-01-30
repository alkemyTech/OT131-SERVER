package com.alkemy.ong.dto;

import javax.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    
    @NotNull(message = "Name cannot be null")
    @NotEmpty(message = "Name cannot be Empty" )
    private String name;

    private String facebookUrl;
    private String instagramUrl;
    private String linkedinUrl;

    @NotNull(message = "Image cannot be null")
    @NotEmpty(message = "Image cannot be Empty")
    private String image;

    private String description;

}
