package com.alkemy.ong.service;

import com.alkemy.ong.dto.CommentsDTO;
import com.alkemy.ong.dto.CommentsResponseDTO;
import com.alkemy.ong.dto.AllCommentsResponseDTO;
import com.alkemy.ong.dto.NewCommentsDTO;
import com.alkemy.ong.dto.UsersDtoResponse;
import com.alkemy.ong.exception.NoDataDisplayException;
import com.alkemy.ong.exception.ParamNotFoundException;
import com.alkemy.ong.mapper.CommentsMapper;
import com.alkemy.ong.model.Comments;
import com.alkemy.ong.repository.CommentsRepository;
import com.alkemy.ong.repository.NewsRepository;
import static com.alkemy.ong.util.Constants.ERROR_EXIST;
import static com.alkemy.ong.util.Constants.ERR_NEWS_NOT_FOUND;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.nio.file.AccessDeniedException;
import java.util.Optional;
import static com.alkemy.ong.util.Constants.FORBIDDEN_MSG;
import static com.alkemy.ong.util.RoleName.ROLE_ADMIN;
import java.util.List;

@Service
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private CommentsMapper commentsMapper;

    @Autowired
    private UsersService usersService;

    @Autowired
    private NewsRepository newsRepository;

    @Override
    @Transactional
    public CommentsResponseDTO create(NewCommentsDTO dto, String authorization) {
        //verificar news existente
        UsersDtoResponse userLogged = usersService.getUserDetails(authorization);
        if (newsRepository.findById(dto.getNewsId()).isPresent()) {

            Comments comment = commentsMapper.comments2Entity(
                    newsRepository.findById(dto.getNewsId()).get(),
                    usersService.findByMail(userLogged.getEmail()).get(),
                    dto.getBody());

            commentsRepository.save(comment);

            return commentsMapper.entity2ResponseDTO(comment);

        } else {
            throw new ParamNotFoundException(ERR_NEWS_NOT_FOUND);
        }

    }

    @Override
    @Transactional
    public CommentsResponseDTO update(Long id, CommentsDTO dto, String authorization) throws AccessDeniedException {

        Optional<Comments> result = commentsRepository.findById(id);
        if (result.isEmpty()) {
            return null;
        }

        Comments entity = result.get();
        verifyComment(entity, dto, authorization);

        entity.setBody(dto.getBody());
        Comments entityUpdated = commentsRepository.save(entity);

        return commentsMapper.entity2ResponseDTO(entityUpdated);
    }

    private void verifyComment(Comments entity, CommentsDTO dto, String authorization) throws AccessDeniedException {

        UsersDtoResponse userLogged = usersService.getUserDetails(authorization);

        if (!entity.getUsers().getId().equals(dto.getUserId()) || !entity.getNews().getId().equals(dto.getNewsId())
                || !(userLogged.getEmail().equals(entity.getUsers().getEmail()) || userLogged.getRole().getName().equals(ROLE_ADMIN))) {
            throw new AccessDeniedException(FORBIDDEN_MSG);
        }
    }

    @Override
    public List<AllCommentsResponseDTO> getNewAndAllComment(Long id) {
        List<Comments> listComments = commentsRepository.getNewsAndAllComments(id);
        if (!listComments.isEmpty()) {
            return commentsMapper.allCommentsResponseDtoToCommnets(listComments);
        } else {
            throw new NoDataDisplayException(ERROR_EXIST);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<List<String>> listComments() throws AccessDeniedException {
        return commentsRepository.listComments();
    }

    @Transactional
    @Override
    public void delete(Long id, String authorization) throws AccessDeniedException {
        Optional<Comments> result = commentsRepository.findById(id);
        UsersDtoResponse userLogged = usersService.getUserDetails(authorization);
        if (result.isPresent() && userLogged.getEmail().equals(result.get().getUsers().getEmail()) || userLogged.getRole().getName().equals(ROLE_ADMIN)) {
            commentsRepository.deleteById(id);
        } else {
            throw new AccessDeniedException(FORBIDDEN_MSG);
        }
    }

}
