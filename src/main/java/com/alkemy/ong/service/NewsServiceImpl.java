package com.alkemy.ong.service;

import com.alkemy.ong.dto.NewsDTO;
import com.alkemy.ong.dto.PagesDTO;
import com.alkemy.ong.exception.ParamNotFoundException;
import com.alkemy.ong.mapper.NewsMapper;
import com.alkemy.ong.model.Categories;
import com.alkemy.ong.model.News;
import com.alkemy.ong.repository.CategoriesRepository;
import com.alkemy.ong.repository.NewsRepository;
import static com.alkemy.ong.util.Constants.NEWS_PAGE_URL;
import static com.alkemy.ong.util.Constants.PAGE_NOT_FOUND;
import static com.alkemy.ong.util.Constants.PAGE_SIZE;
import static com.alkemy.ong.util.Constants.WRONG_PAGE_NUMBER;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private CategoriesRepository categoriesRepository;
    @Autowired
    private NewsMapper newsMapper;

    private ModelMapper mapper = new ModelMapper();

    @Override
    public NewsDTO publicDataNew(String name) {
        Optional<News> nw = newsRepository.findByName(name);
        return newsMapper.converToDTO(nw.get());
    }

    @Transactional
    @Override
    public void deleteNew(Long id) {
        Optional<News> answer = newsRepository.findById(id);
        System.out.println(answer.toString());
        if (answer.isPresent()) {
            News news = answer.get();
            news.setIsActivated(Boolean.FALSE);
            newsRepository.save(news);
        } else {
            throw new ParamNotFoundException("News does not exist");
        }

    }

    @Transactional
    public NewsDTO save(NewsDTO dto) {

        News entity = newsMapper.converToModel(dto);

        Optional<Categories> categoriesResult = categoriesRepository.findById(dto.getIdCategory());
        if (categoriesResult.isEmpty()) {
            throw new ParamNotFoundException("The News must have a valid category id");
        }

        entity.setCategory(categoriesResult.get());
        News entitySaved = newsRepository.save(entity);

        return newsMapper.converToDTO(entitySaved);
    }

    @Transactional
    @Override
    public NewsDTO update(Long id, NewsDTO dto) {

        Optional<News> result = newsRepository.findById(id);
        if (result.isPresent()) {
            News entity = mapper.map(dto, News.class);
            News entityUpdated = result.get();
            entityUpdated.setDateModified(LocalDate.now());
            entityUpdated.setContent(entity.getContent());
            entityUpdated.setImage(entity.getImage());
            entityUpdated.setName(entity.getName());
            newsRepository.save(entityUpdated);

            return mapper.map(entityUpdated, NewsDTO.class);
        } else {
            throw new ParamNotFoundException("Requested new was not found.");
        }

    }

    @Override
    public NewsDTO findById(Long id) {
        Optional<News> news = newsRepository.findById(id);
        if (news.isPresent()) {
            NewsDTO newsDto = newsMapper.converToDTO(news.get());
            return newsDto;
        } else {
            throw new ParamNotFoundException("News with id=" + id + " not found.");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public PagesDTO<NewsDTO> getAll(Integer page) {
        if (page < 0) {
            throw new ParamNotFoundException(WRONG_PAGE_NUMBER);
        }

        Pageable pageRequest = PageRequest.of(page, PAGE_SIZE);
        Page<News> news = newsRepository.findAll(pageRequest);

        return this.responsePage(news);
    }

    private PagesDTO<NewsDTO> responsePage(Page<News> page) {
        if (page.isEmpty()) {
            throw new ParamNotFoundException(PAGE_NOT_FOUND);
        }

        Page<NewsDTO> responsePage = new PageImpl(
                newsMapper.listNewsToDto(page.getContent()),
                PageRequest.of(page.getNumber(), page.getSize()),
                page.getTotalElements());

        return new PagesDTO<>(responsePage, NEWS_PAGE_URL);
    }
}
