package com.alkemy.ong.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentsDTO {

    @NotNull(message = "User ID is required.")
    private Long userId;

    @NotNull(message = "The news id is required.")
    private Long newsId;

    @NotEmpty(message = "The body of the comment can't be empty")
    private String body;
}
