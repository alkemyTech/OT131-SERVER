package com.alkemy.ong.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsersOkDto implements Serializable {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

}
