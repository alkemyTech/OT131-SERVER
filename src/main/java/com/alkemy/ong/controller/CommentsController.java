package com.alkemy.ong.controller;

import com.alkemy.ong.dto.CommentsDTO;
import com.alkemy.ong.dto.CommentsResponseDTO;
import com.alkemy.ong.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.alkemy.ong.util.Constants.REQ_MAPP_COMMENTS;
import static com.alkemy.ong.util.Constants.REQ_MAPP_ID;

@RestController
@RequestMapping(REQ_MAPP_COMMENTS)
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    @PutMapping(REQ_MAPP_ID)
    public ResponseEntity<CommentsResponseDTO> update(@PathVariable Long id,
                                                      @Valid @RequestBody CommentsDTO dto,
                                                      HttpServletRequest request) {
        CommentsResponseDTO result = commentsService.update(id, dto, request.getHeader("Authorization"));
        return ResponseEntity.ok().body(result);
    }

}
