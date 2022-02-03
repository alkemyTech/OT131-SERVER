package com.alkemy.ong.service;

import com.alkemy.ong.exception.ParamNotFoundException;
import com.alkemy.ong.model.Testimonials;
import com.alkemy.ong.repository.TestimonialsRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestimonialsServiceImpl implements TestimonialsService {

    @Autowired
    TestimonialsRepository testimonialsRepository;

    @Override
    public Testimonials save(Testimonials testimonials) {
        return testimonialsRepository.save(testimonials);
    }

    @Override
    public Testimonials update(Testimonials testimonials) {
        return testimonialsRepository.save(testimonials);
    }

    @Override
    public void delete(Long id) {
        Optional<Testimonials> testimonials = testimonialsRepository.findById(id);
        if (testimonials.isPresent()) {
            testimonials.get().setIsActive(false);
            testimonialsRepository.save(testimonials.get());
        }else{
            throw new ParamNotFoundException("Testimonial does not exist");
        }

    }



}
