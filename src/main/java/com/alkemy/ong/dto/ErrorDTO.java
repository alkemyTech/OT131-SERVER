package com.alkemy.ong.dto;

import java.util.List;
import org.springframework.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDTO {

    private HttpStatus status;
    private List<String> errors;

}
