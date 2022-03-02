package com.alkemy.ong.service;

import com.alkemy.ong.dto.PagesDTO;
import com.alkemy.ong.dto.TestimonialsAddNewDto;
import com.alkemy.ong.dto.TestimonialsDto;
import com.alkemy.ong.dto.TestimonialsResponseDto;
import com.alkemy.ong.exception.AlreadyExistsException;
import com.alkemy.ong.exception.ParamNotFoundException;
import com.alkemy.ong.mapper.TestimonialsMapper;
import com.alkemy.ong.model.Testimonials;
import com.alkemy.ong.repository.TestimonialsRepository;

import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.alkemy.ong.util.Constants.*;

@Service
public class TestimonialsServiceImpl implements TestimonialsService {

    @Autowired
    private TestimonialsRepository testimonialsRepository;

    @Autowired
    private TestimonialsMapper testimonalsMapper;

    private ModelMapper mapper = new ModelMapper();

    @Transactional
    @Override
    public TestimonialsDto save(TestimonialsAddNewDto testimonialsAddNewDto) {
        if (testimonialsRepository.findByName(testimonialsAddNewDto.getName()) == null) {
            return mapper.map(testimonialsRepository.save(mapper.map(testimonialsAddNewDto, Testimonials.class)), TestimonialsDto.class);
        } else {
            throw new AlreadyExistsException(ERROR_EXIST);
        }
    }

    @Transactional
    @Override
    public TestimonialsDto updateTestimonails(TestimonialsDto testimonials, long id) {
        Optional<Testimonials> testimonial = testimonialsRepository.findById(id);
        if (testimonial.isEmpty()) {
            throw new ParamNotFoundException(ENTITY_NOT_FOUND);
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
        } else {
            throw new ParamNotFoundException("Testimonial does not exist");
        }

    }

    @Override
    @Transactional(readOnly = true)
    public PagesDTO<TestimonialsResponseDto> getAll(Integer page) {

        if (page < 0) {
            throw new ParamNotFoundException(WRONG_PAGE_NUMBER);
        }

        Pageable pageRequest = PageRequest.of(page, PAGE_SIZE);
        Page<Testimonials> testimonials = testimonialsRepository.findAll(pageRequest);

        return responsePage(testimonials);
    }

    private PagesDTO<TestimonialsResponseDto> responsePage(Page<Testimonials> page) {

        if (page.isEmpty()) {
            throw new ParamNotFoundException(PAGE_NOT_FOUND);
        }

        Page<TestimonialsResponseDto> response = new PageImpl<>(
                testimonalsMapper.entityList2ResponseDTO(page.getContent()),
                PageRequest.of(page.getNumber(), page.getSize()),
                page.getTotalElements()
        );
        return new PagesDTO<>(response, TESTIMONIALS_PAGE_URL);
    }

}
