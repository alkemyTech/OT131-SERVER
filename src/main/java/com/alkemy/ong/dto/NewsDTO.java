package com.alkemy.ong.dto;

import com.alkemy.ong.model.Categories;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsDTO {

    private String name;
    private String content;
    private String image;
    private Categories category;
    private LocalDate dateCreated;
}
