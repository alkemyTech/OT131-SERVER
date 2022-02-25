package com.alkemy.ong.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.io.IOException;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import com.alkemy.ong.dto.MemberDTO;
import com.alkemy.ong.dto.NewMemberDTO;
import static com.alkemy.ong.util.Constants.*;
import com.alkemy.ong.exception.AlreadyExistsException;
import com.alkemy.ong.exception.ParamNotFoundException;
import com.alkemy.ong.model.Members;
import com.alkemy.ong.repository.MembersRepository;
import com.alkemy.ong.service.MembersService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)// Se extienda con spring de junit5
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application_test.properties")
public class MembersControllerTest {

    @Autowired
    private MembersRepository membersRepository;

    @MockBean
    private MembersService membersService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; //Para serializar deserializar

    private NewMemberDTO memberDTO;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        this.memberDTO = NewMemberDTO.builder()
                .name("Name Member")
                .image("ImageMember.jpg")
                .description("description Member")
                .instagramUrl("instagramUrl Member")
                .facebookUrl("facebookUrl Member")
                .linkedinUrl("linkedinUrl Member")
                .build();
        Mockito.when(membersService.updateMember(memberDTO, 2l)).thenThrow(ParamNotFoundException.class);
        Mockito.doThrow(ParamNotFoundException.class).when(membersService).deleteMember(2l);
        Mockito.when(membersService.getAll(2)).thenThrow(ParamNotFoundException.class);

    }

    @WithMockUser(roles = {ADMIN_ROLE, USER_ROLE})
    @Test
    public void shouldFailedWhenSavedWhitInstagramFacebookOrLinkedInExist() throws IOException, Exception {
        Members memberEntity = Members.builder().idMember(1l).name("Name Member").image("ImageMember.jpg")
                .description("description Member").instagramUrl("instagramUrl Member").facebookUrl("facebookUrl Member")
                .linkedinUrl("linkedinUrl Member").build();

        membersRepository.save(memberEntity);

        Mockito.when(membersService.createMember(memberDTO)).thenThrow(AlreadyExistsException.class);
        assertThat(memberEntity.getFacebookUrl()).isEqualTo(memberDTO.getFacebookUrl());
        assertThat(memberEntity.getInstagramUrl()).isEqualTo(memberDTO.getInstagramUrl());
        assertThat(memberEntity.getLinkedinUrl()).isEqualTo(memberDTO.getLinkedinUrl());

        mockMvc.perform(post(REQ_MAPP_MEMBERS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(memberDTO)))
                .andExpect(status().isBadRequest());

    }

    @WithMockUser(roles = {ADMIN_ROLE, USER_ROLE})
    @Test
    public void shouldSavedMemberOK() throws Exception {
        Mockito.when(membersService.createMember(any())).thenReturn(memberDTO);

        mockMvc.perform(((post(REQ_MAPP_MEMBERS))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(memberDTO))))//PARA EL BODY
                .andExpect(status().isCreated());
        verify(membersService, times(1)).createMember(memberDTO);
    }

    @Test
    @WithMockUser(roles = {ADMIN_ROLE, USER_ROLE})
    public void shouldFailedWhenSaveWithNullData() throws Exception {

        mockMvc.perform(
                post(REQ_MAPP_MEMBERS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new MemberDTO())))
                .andExpect(status().isBadRequest());
        verify(membersService, times(0)).createMember(new NewMemberDTO());
    }

    @Test
    @WithMockUser(roles = {ADMIN_ROLE, USER_ROLE})
    public void shouldUpdateWithAnyRole() throws Exception {

        mockMvc.perform(put(REQ_MAPP_MEMBERS + REQ_MAPP_ID, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(memberDTO)))//PARA EL BODY
                .andExpect(status().isOk());

        verify(membersService, times(1)).updateMember(memberDTO, 1L);
    }

    @Test
    @WithMockUser(roles = {ADMIN_ROLE, USER_ROLE})
    public void shouldFailedUpdateWhenidNoExist() throws Exception {

        mockMvc.perform(put(REQ_MAPP_MEMBERS + REQ_MAPP_ID, 2)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
        verify(membersService, times(0)).updateMember(memberDTO, 2L);
    }

    @Test
    @WithMockUser(roles = ADMIN_ROLE)
    public void shouldDeleteWithAdminRole() throws Exception {

        mockMvc.perform(delete(REQ_MAPP_MEMBERS + REQ_MAPP_ID, 1))
                .andExpect(status().isOk());
        verify(membersService, times(1)).deleteMember(1L);
    }

    @Test
    @WithMockUser(roles = USER_ROLE)
    public void shouldFailedDeleteWithUserRole() throws Exception {
        mockMvc.perform(delete(REQ_MAPP_MEMBERS + REQ_MAPP_ID, 1))
                .andExpect(status().isForbidden());
        verify(membersService, times(0)).deleteMember(1L);
    }

    @Test
    @WithMockUser(roles = ADMIN_ROLE)
    public void shouldFailedDeleteWhenIdNoExist() throws Exception {
        mockMvc.perform(delete(REQ_MAPP_MEMBERS + REQ_MAPP_ID, 2)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
        verify(membersService, times(1)).deleteMember(2l);
    }

    @Test
    @WithMockUser(roles = {ADMIN_ROLE, USER_ROLE})
    public void getAllWithAdminRoleAndUser() throws Exception {

        this.mockMvc.perform(get(REQ_MAPP_MEMBERS + URL_PAGE_TEST, 1))
                .andExpect(status().isOk());
        verify(membersService, times(1)).getAll(1);
    }

    @Test
    public void getAllWithoutRole() throws Exception {

        this.mockMvc.perform(get(REQ_MAPP_MEMBERS + URL_PAGE_TEST, 1))
                .andExpect(status().isForbidden());

        verify(membersService, times(0)).getAll(1);
    }

    @Test
    @WithMockUser(roles = {ADMIN_ROLE, USER_ROLE})
    public void getAllWithNonexistentId() throws Exception {

        this.mockMvc.perform(get(REQ_MAPP_MEMBERS + URL_PAGE_TEST, 2))
                .andExpect(status().isBadRequest());
        verify(membersService, times(1)).getAll(2);
    }

}
