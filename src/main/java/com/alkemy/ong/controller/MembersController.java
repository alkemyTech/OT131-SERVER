package com.alkemy.ong.controller;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.alkemy.ong.util.Constants.REQ_MAPP_MEMBERS;

import java.util.List;

import static com.alkemy.ong.util.Constants.GET_MAPP_LIST_MEMBERS;

import com.alkemy.ong.dto.MemberDTO;
import com.alkemy.ong.service.MembersService;

@RestController
@RequestMapping(REQ_MAPP_MEMBERS)
public class MembersController {

    @Autowired
    MembersService membersService;

    @GetMapping(GET_MAPP_LIST_MEMBERS)
    public ResponseEntity<List<MemberDTO>> listMembers() {

        return ResponseEntity.status(HttpStatus.SC_OK).body(membersService.getMembers());

    }

}
