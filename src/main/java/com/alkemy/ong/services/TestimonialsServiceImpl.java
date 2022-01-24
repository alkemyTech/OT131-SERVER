package com.alkemy.ong.services;

import com.alkemy.ong.model.Testimonials;
import com.alkemy.ong.repository.TestimonialsRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestimonialsServiceImpl implements ITestimonialsService {

    @Autowired
    TestimonialsRepository testimonialRepository;

    @Override
    public Testimonials save(Testimonials testimonials) {
        return testimonialRepository.save(testimonials);
    }

    @Override
    public Testimonials update(Testimonials testimonials) {
        return testimonialRepository.save(testimonials);
    }

    @Override
    public void delete(Long id) {
        Optional<Testimonials> testimonials = testimonialRepository.findById(id);
        if (testimonials.isPresent()) {
            testimonials.get().setIsActive(false);
            testimonialRepository.save(testimonials.get());
        }

    }

    @Override
    public Testimonials select(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
