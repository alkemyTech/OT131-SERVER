package com.alkemy.ong.controller;

import static com.alkemy.ong.util.Constants.*;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.alkemy.ong.dto.OrganizationsAllDTO;
import com.alkemy.ong.dto.OrganizationsDTO;
import com.alkemy.ong.exception.ParamNotFoundException;
import com.alkemy.ong.service.OrganizationsService;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class OrganizationsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrganizationsService orgService;
    
    @Autowired
    private JacksonTester<OrganizationsAllDTO> json;

    private OrganizationsAllDTO organizationsDTO;
    private OrganizationsDTO dto;

    @BeforeEach
    void setUp() throws Exception {

        this.organizationsDTO = OrganizationsAllDTO.builder()
                .name(FOO_STRING)
                .images(FOO_STRING)
                .email(MAIL_ONG)
                .welcomeText(FOO_STRING)
                .build();
        
        this.dto = OrganizationsDTO.builder()
                .name(FOO_STRING)
                .images(FOO_STRING)
                .addres(FOO_STRING)
                .facebookUrl(FOO_STRING)
                .instagramUrl(FOO_STRING)
                .linkedinUrl(FOO_STRING)
                .phone(0)
                .build();
        
        when(orgService.updateDataOrganization(organizationsDTO, 2L)).thenThrow(new ParamNotFoundException(EMPTY_STRING));
        when(orgService.publicDataOrganization(organizationsDTO.getName())).thenReturn(dto);
    }

    @Test
    @WithMockUser(roles = ADMIN_ROLE)
    void saveWithAdminRole() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .post(REQ_MAPP_ORG)
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoToJson(organizationsDTO)))
                .andExpect(status().isCreated());

        verify(orgService, times(1)).saveOrganization(organizationsDTO);
    }

    @Test
    @WithMockUser(roles = USER_ROLE)
    void doNotSaveWithUserRole() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .post(REQ_MAPP_ORG)
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoToJson(organizationsDTO)))
                .andExpect(status().isForbidden());
    }

    @Test
    void doNotSaveWithoutRole() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .post(REQ_MAPP_ORG)
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoToJson(organizationsDTO)))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = ADMIN_ROLE)
    void doNotSaveWithNullData() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .post(REQ_MAPP_ORG)
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoToJson(new OrganizationsAllDTO())))
                .andExpect(status().isBadRequest());

        verify(orgService, times(0)).saveOrganization(new OrganizationsAllDTO());
    }

    @Test
    @WithMockUser(roles = ADMIN_ROLE)
    void updateWithAdminRole() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .put(REQ_MAPP_ORG + REQ_MAPP_ID, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoToJson(organizationsDTO)))
                .andExpect(status().isOk());

        verify(orgService, times(1)).updateDataOrganization(organizationsDTO, 1L);
    }

    @Test
    @WithMockUser(roles = USER_ROLE)
    void doNotUpdateWithUserRole() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .put(REQ_MAPP_ORG + REQ_MAPP_ID, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoToJson(organizationsDTO)))
                .andExpect(status().isForbidden());

        verify(orgService, times(0)).updateDataOrganization(organizationsDTO, 1);
    }

    @Test
    void doNotUpdateWithoutRole() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .put(REQ_MAPP_ORG + REQ_MAPP_ID, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoToJson(organizationsDTO)))
                .andExpect(status().isForbidden());

        verify(orgService, times(0)).updateDataOrganization(organizationsDTO, 1);
    }

    @Test
    @WithMockUser(roles = ADMIN_ROLE)
    void doNotUpdateWithNonExistentId() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .put(REQ_MAPP_ORG + REQ_MAPP_ID, 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoToJson(organizationsDTO)))
                .andExpect(status().isBadRequest());

        verify(orgService, times(1)).updateDataOrganization(organizationsDTO, 2L);
    }

    @Test
    @WithMockUser(roles = ADMIN_ROLE)
    void doNotUpdateWithNullData() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .put(REQ_MAPP_ORG + POINT_POST_MAPP)
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoToJson(new OrganizationsAllDTO())))
                .andExpect(status().isBadRequest());

        verify(orgService, times(0)).updateDataOrganization(new OrganizationsAllDTO(), 1);
    }

    @Test
    @WithMockUser(roles = ADMIN_ROLE)
    void listDataOrganizationWithAdminRole() throws Exception {

        this.mockMvc.perform(get(REQ_MAPP_ORG)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(orgService, times(1)).listOrganizations();
    }

    @Test
    @WithMockUser(roles = USER_ROLE)
    void listDataOrganizationWithUserRole() throws Exception {

        this.mockMvc.perform(get(REQ_MAPP_ORG)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(orgService, times(1)).listOrganizations();
    }

    @Test
    void doNotlistDataOrganizationWithOutRole() throws Exception {

        this.mockMvc.perform(get(REQ_MAPP_ORG)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());

        verify(orgService, times(0)).listOrganizations();
    }

    @Test
    @WithMockUser(roles = ADMIN_ROLE)
    void publicDataOrganizationWithAdminRole() throws Exception {

        this.mockMvc.perform(get(REQ_MAPP_ORG + POINT_GET_MAPP, organizationsDTO.getName())
                .content(dtoToJson(organizationsDTO)))
                .andExpect(status().isOk());

        verify(orgService, times(1)).publicDataOrganization(organizationsDTO.getName());
    }

    @Test
    @WithMockUser(roles = USER_ROLE)
    void publicDataOrganizationWithUserRole() throws Exception {

        this.mockMvc.perform(get(REQ_MAPP_ORG + POINT_GET_MAPP, organizationsDTO.getName())
                .content(dtoToJson(organizationsDTO)))
                .andExpect(status().isOk());

        verify(orgService, times(1)).publicDataOrganization(organizationsDTO.getName());
    }

    @Test
    void publicDataOrganizationWithOutRole() throws Exception {

        this.mockMvc.perform(get(REQ_MAPP_ORG + POINT_GET_MAPP, organizationsDTO.getName())
                .content(dtoToJson(organizationsDTO)))
                .andExpect(status().isForbidden());

        verify(orgService, times(0)).publicDataOrganization(organizationsDTO.getName());
    }

    private String dtoToJson(OrganizationsAllDTO dto) throws IOException {
        return json.write(dto).getJson();
    }
}
