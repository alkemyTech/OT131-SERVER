
package com.alkemy.ong.services;


import com.alkemy.ong.model.Testimonials;

public interface TestimonialsService {


    public Testimonials save(Testimonials testimonials);

    public Testimonials update(Testimonials testimonials);

    public void delete(Long id);

    public Testimonials select(Long id);
}
