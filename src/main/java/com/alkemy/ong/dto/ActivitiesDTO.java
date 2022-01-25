package com.alkemy.ong.dto;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivitiesDTO {

    private Long id;
    @NotBlank(message = "The name must be valid")
    private String name;
    @NotBlank(message = "The content must be valid")
    private String content;
    @NotBlank(message = "The image must be valid")
    private String image;
    private String createdDate;
    private String modifiedDate;
}
