package com.alkemy.ong.dto;

import static com.alkemy.ong.util.Constants.NAME_NOT_NULL_MESSAGE;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestimonialsDto implements Serializable {

    private Long id;

    @NotNull(message = NAME_NOT_NULL_MESSAGE)
    private String name;

    private String image;

    private String content;

}
