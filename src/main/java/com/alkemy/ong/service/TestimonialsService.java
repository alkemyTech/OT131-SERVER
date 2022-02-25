package com.alkemy.ong.service;

import com.alkemy.ong.dto.PagesDTO;
import com.alkemy.ong.dto.TestimonialsAddNewDto;
import com.alkemy.ong.dto.TestimonialsDto;
import com.alkemy.ong.dto.TestimonialsResponseDto;

public interface TestimonialsService {

    public TestimonialsDto save(TestimonialsAddNewDto testimonialsAddNewDto);

    public TestimonialsDto updateTestimonails(TestimonialsDto testimonials, long id);

    public void delete(Long id);

    PagesDTO<TestimonialsResponseDto> getAll(Integer page);
}
