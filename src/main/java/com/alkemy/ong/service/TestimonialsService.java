
package com.alkemy.ong.service;


import com.alkemy.ong.dto.TestimonialsDto;
import com.alkemy.ong.model.Testimonials;

public interface TestimonialsService {


    public Testimonials save(Testimonials testimonials);

    public TestimonialsDto updateTestimonails(TestimonialsDto testimonials,long id);

    public void delete(Long id);


}
