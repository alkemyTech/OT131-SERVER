package com.alkemy.ong.controller;

import com.alkemy.ong.dto.ActivitiesDTO;
import com.alkemy.ong.exception.ParamNotFoundException;
import com.alkemy.ong.service.ActivitiesService;
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

import static com.alkemy.ong.util.Constants.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.io.IOException;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ActivitiesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ActivitiesService activitiesService;

    @Autowired
    private JacksonTester<ActivitiesDTO> json;

    private ActivitiesDTO activitiesDTO;

    @BeforeEach
    void setUp() {

        this.activitiesDTO = ActivitiesDTO.builder()
                .name(FOO_STRING)
                .content(FOO_STRING)
                .image(FOO_STRING)
                .build();

        when(activitiesService.update(2L, activitiesDTO)).thenThrow(new ParamNotFoundException(EMPTY_STRING));
        doThrow(new ParamNotFoundException(EMPTY_STRING)).when(activitiesService).delete(2L);
    }

    @Test
    @WithMockUser(roles = ADMIN_ROLE)
    void saveWithAdminRole() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .post(REQ_MAPP_ACTIVITIES)
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoToJson(activitiesDTO)))
                .andExpect(status().isCreated());

        verify(activitiesService, times(1)).save(activitiesDTO);
    }

    @Test
    @WithMockUser(roles = USER_ROLE)
    void doNotSaveWithUserRole() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .post(REQ_MAPP_ACTIVITIES)
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoToJson(activitiesDTO)))
                .andExpect(status().isForbidden());
    }

    @Test
    void doNotSaveWithoutRole() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .post(REQ_MAPP_ACTIVITIES)
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoToJson(activitiesDTO)))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = ADMIN_ROLE)
    void doNotSaveWithNullData() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .post(REQ_MAPP_ACTIVITIES)
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoToJson(new ActivitiesDTO())))
                .andExpect(status().isBadRequest());

        verify(activitiesService, times(0)).save(activitiesDTO);
    }

    @Test
    @WithMockUser(roles = ADMIN_ROLE)
    void getAllActivesWithAdminRole() throws Exception {

        this.mockMvc.perform(get(REQ_MAPP_ACTIVITIES)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(activitiesService, times(1)).getAllActives();
    }

    @Test
    @WithMockUser(roles = USER_ROLE)
    void getAllActivesWithUserRole() throws Exception {

        this.mockMvc.perform(get(REQ_MAPP_ACTIVITIES)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(activitiesService, times(1)).getAllActives();
    }

    @Test
    void doNotGetAllActivesWithoutRole() throws Exception {

        this.mockMvc.perform(get(REQ_MAPP_ACTIVITIES)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());

    }

    @Test
    @WithMockUser(roles = ADMIN_ROLE)
    void updateWithAdminRole() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .put(REQ_MAPP_ACTIVITIES + REQ_MAPP_ID, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoToJson(activitiesDTO)))
                .andExpect(status().isOk());

        verify(activitiesService, times(1)).update(1L, activitiesDTO);
    }

    @Test
    @WithMockUser(roles = USER_ROLE)
    void doNotUpdateWithUserRole() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .put(REQ_MAPP_ACTIVITIES + REQ_MAPP_ID, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoToJson(activitiesDTO)))
                .andExpect(status().isForbidden());

        verify(activitiesService, times(0)).update(1L, activitiesDTO);
    }

    @Test
    void doNotUpdateWithoutRole() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .put(REQ_MAPP_ACTIVITIES + REQ_MAPP_ID, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoToJson(activitiesDTO)))
                .andExpect(status().isForbidden());

        verify(activitiesService, times(0)).update(1L, activitiesDTO);
    }

    @Test
    @WithMockUser(roles = ADMIN_ROLE)
    void doNotUpdateWithNonExistentId() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .put(REQ_MAPP_ACTIVITIES + REQ_MAPP_ID, 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoToJson(activitiesDTO)))
                .andExpect(status().isBadRequest());

        verify(activitiesService, times(1)).update(2L, activitiesDTO);
    }

    @Test
    @WithMockUser(roles = ADMIN_ROLE)
    void doNotUpdateWithNullData() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .put(REQ_MAPP_ACTIVITIES + REQ_MAPP_ID, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoToJson(new ActivitiesDTO())))
                .andExpect(status().isBadRequest());

        verify(activitiesService, times(0)).update(1L, new ActivitiesDTO());
    }

    @Test
    @WithMockUser(roles = ADMIN_ROLE)
    void deleteWithAdminRole() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .delete(REQ_MAPP_ACTIVITIES + REQ_MAPP_ID, 1))
                .andExpect(status().isNoContent());

        verify(activitiesService, times(1)).delete(1L);
    }

    @Test
    @WithMockUser(USER_ROLE)
    void doNotDeleteWithUserRole() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .delete(REQ_MAPP_ACTIVITIES + REQ_MAPP_ID, 1))
                .andExpect(status().isForbidden());

        verify(activitiesService, times(0)).delete(1L);
    }

    @Test
    void doNotDeleteWithoutRole() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .delete(REQ_MAPP_ACTIVITIES + REQ_MAPP_ID, 1))
                .andExpect(status().isForbidden());

        verify(activitiesService, times(0)).delete(1L);
    }

    @Test
    @WithMockUser(roles = ADMIN_ROLE)
    void doNotDeleteWithNonExistentId() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .delete(REQ_MAPP_ACTIVITIES + REQ_MAPP_ID, 2))
                .andExpect(status().isBadRequest());

        verify(activitiesService, times(1)).delete(2L);
    }

    private String dtoToJson(ActivitiesDTO dto) throws IOException {
        return json.write(dto).getJson();
    }
}
