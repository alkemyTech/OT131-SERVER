package com.alkemy.ong.service;

import com.alkemy.ong.dto.TestimonialsAddNewDto;
import com.alkemy.ong.dto.TestimonialsDto;
import com.alkemy.ong.exception.AlreadyExistsException;
import com.alkemy.ong.exception.ParamNotFoundException;
import com.alkemy.ong.model.Testimonials;
import com.alkemy.ong.repository.TestimonialsRepository;
import static com.alkemy.ong.util.Constants.ENTITY_NOT_FOUND;
import static com.alkemy.ong.util.Constants.ERROR_EXIST;
import java.util.Optional;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestimonialsServiceImpl implements TestimonialsService {

    @Autowired
    TestimonialsRepository testimonialsRepository;
    
    private ModelMapper mapper = new ModelMapper();

    @Transactional
    @Override
    public TestimonialsDto save(TestimonialsAddNewDto testimonialsAddNewDto) {
        if(testimonialsRepository.findByName(testimonialsAddNewDto.getName()) != null){
            return mapper.map(testimonialsRepository.save(mapper.map(testimonialsAddNewDto, Testimonials.class)), TestimonialsDto.class);
        }else{
            throw new AlreadyExistsException(ERROR_EXIST);
        }
    }

    @Transactional
    @Override
    public TestimonialsDto updateTestimonails(TestimonialsDto testimonials, long id) {
    	Optional<Testimonials> testimonial = testimonialsRepository.findById(id);
    	if (testimonial.isEmpty()) {
    		throw new ParamNotFoundException (ENTITY_NOT_FOUND);
    	}
    	testimonial.get().setImage(testimonials.getImage());
    	testimonial.get().setContent(testimonials.getContent());
    	testimonial.get().setName(testimonials.getName());
    	testimonialsRepository.save(testimonial.get());
    	return mapper.map(testimonial.get(), TestimonialsDto.class);
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
