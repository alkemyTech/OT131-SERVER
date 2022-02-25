package com.alkemy.ong.dto;

import static com.alkemy.ong.util.Constants.CONTENT_NOT_EMPTY_MESSAGE;
import static com.alkemy.ong.util.Constants.CONTENT_NOT_NULL_MESSAGE;
import static com.alkemy.ong.util.Constants.NAME_NOT_EMPTY_MESSAGE;
import static com.alkemy.ong.util.Constants.NAME_NOT_NULL_MESSAGE;
import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TestimonialsAddNewDto implements Serializable {

    @NotNull(message = NAME_NOT_NULL_MESSAGE)
    @NotEmpty(message = NAME_NOT_EMPTY_MESSAGE)
    private String name;

    private String image;

    @NotNull(message = CONTENT_NOT_NULL_MESSAGE)
    @NotEmpty(message = CONTENT_NOT_EMPTY_MESSAGE)
    private String content;

}
