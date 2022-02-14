package com.alkemy.ong.dto;

import lombok.Data;

@Data
public class AllCommentsResponseDTO {

    private Long userId;

    private String body;

    private String dateCreated;
}
