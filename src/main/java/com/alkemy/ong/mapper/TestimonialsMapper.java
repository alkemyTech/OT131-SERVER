package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.TestimonialsResponseDto;
import com.alkemy.ong.model.Testimonials;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TestimonialsMapper {

    private ModelMapper mapper = new ModelMapper();

    public List<TestimonialsResponseDto> entityList2ResponseDTO(List<Testimonials> entities) {
        return entities.stream().map(testimonials
                -> mapper.map(testimonials, TestimonialsResponseDto.class))
                .collect(Collectors.toList());
    }
}
