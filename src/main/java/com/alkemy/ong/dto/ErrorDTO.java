package com.alkemy.ong.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErrorDTO {
    
    private HttpStatus status;
    private List<String> errors;
    
}
