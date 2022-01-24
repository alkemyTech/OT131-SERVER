package com.alkemy.ong.dto;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivitiesDTO {

    private Long id;
    private String name;
    private String content;
    private String image;
    private String creationDate;
    private String modifiedDate;
}
