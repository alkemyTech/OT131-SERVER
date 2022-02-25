package com.alkemy.ong.dto;

import javax.validation.constraints.*;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewMemberDTO {

    @NotBlank(message = "Name cannot be null or empty")
    @Pattern(regexp = "[a-zA-Z\\sÃ¡Ã©Ã­Ã³ÃºÃ�Ã‰Ã�Ã“ÃšÃ‘Ã±]*", message = "The name can only contain letters")
    private String name;

    private String facebookUrl;
    private String instagramUrl;
    private String linkedinUrl;

    @NotNull(message = "Image cannot be null or empty")
    private String image;

    private String description;

}
