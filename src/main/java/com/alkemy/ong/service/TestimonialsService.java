
package com.alkemy.ong.service;


import com.alkemy.ong.model.Testimonials;

public interface TestimonialsService {


    public Testimonials save(Testimonials testimonials);

    public Testimonials update(Testimonials testimonials);

    public void delete(Long id);


}
