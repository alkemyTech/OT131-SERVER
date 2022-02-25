package com.alkemy.ong.controller;

import com.alkemy.ong.dto.CategoriesDTO;
import com.alkemy.ong.exception.ParamNotFoundException;
import com.alkemy.ong.service.CategoriesService;
import static com.alkemy.ong.util.Constants.*;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class CategoriesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoriesService categoriesService;

    @Autowired
    private JacksonTester<CategoriesDTO> json;

    private CategoriesDTO categoriesDTO;

    @BeforeEach
    public void setUp() throws Exception {
        categoriesDTO = new CategoriesDTO();
        categoriesDTO.setImage("image.jpg");
        categoriesDTO.setName("name");
        categoriesDTO.setDescription("description");

        when(categoriesService.update(2L, categoriesDTO)).thenThrow(new ParamNotFoundException(EMPTY_STRING));
        doThrow(new ParamNotFoundException(EMPTY_STRING)).when(categoriesService).deleteCategory(2L);
    }

    @Test
    @WithMockUser(roles = ADMIN_ROLE)
    public void saveWithAdminRole() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .post(REQ_MAPP_CATEGORIES)
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoToJson(categoriesDTO)))
                .andExpect(status().isOk());

        verify(categoriesService, times(1)).addCategories(categoriesDTO);
    }

    @Test
    @WithMockUser(roles = USER_ROLE)
    void doNotSaveWithUserRole() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .post(REQ_MAPP_CATEGORIES)
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoToJson(categoriesDTO)))
                .andExpect(status().isForbidden());
    }

    @Test
    void doNotSaveWithoutRole() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .post(REQ_MAPP_CATEGORIES)
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoToJson(categoriesDTO)))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = ADMIN_ROLE)
    void doNotSaveWithNullData() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .post(REQ_MAPP_CATEGORIES)
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoToJson(new CategoriesDTO())))
                .andExpect(status().isBadRequest());

        verify(categoriesService, times(0)).addCategories(categoriesDTO);
    }

    @Test
    @WithMockUser(roles = ADMIN_ROLE)
    void getAllActivesWithAdminRole() throws Exception {

        this.mockMvc.perform(get(REQ_MAPP_CATEGORIES)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(categoriesService, times(1)).getAllByName();
    }

    @Test
    @WithMockUser(roles = USER_ROLE)
    void getAllActivesWithUserRole() throws Exception {

        this.mockMvc.perform(get(REQ_MAPP_CATEGORIES)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(categoriesService, times(1)).getAllByName();
    }

    @Test
    void doNotGetAllActivesWithoutRole() throws Exception {

        this.mockMvc.perform(get(REQ_MAPP_CATEGORIES)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = ADMIN_ROLE)
    void updateWithAdminRole() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .put(REQ_MAPP_CATEGORIES + REQ_MAPP_ID, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoToJson(categoriesDTO)))
                .andExpect(status().isOk());

        verify(categoriesService, times(1)).update(1L, categoriesDTO);
    }

    @Test
    @WithMockUser(roles = USER_ROLE)
    void doNotUpdateWithUserRole() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .put(REQ_MAPP_CATEGORIES + REQ_MAPP_ID, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoToJson(categoriesDTO)))
                .andExpect(status().isForbidden());

        verify(categoriesService, times(0)).update(1L, categoriesDTO);
    }

    @Test
    void doNotUpdateWithoutRole() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .put(REQ_MAPP_CATEGORIES + REQ_MAPP_ID, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoToJson(categoriesDTO)))
                .andExpect(status().isForbidden());

        verify(categoriesService, times(0)).update(1L, categoriesDTO);
    }

    @Test
    @WithMockUser(roles = ADMIN_ROLE)
    void doNotUpdateWithNonExistentId() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .put(REQ_MAPP_CATEGORIES + REQ_MAPP_ID, 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoToJson(categoriesDTO)))
                .andExpect(status().isBadRequest());

        verify(categoriesService, times(1)).update(2L, categoriesDTO);
    }

    @Test
    @WithMockUser(roles = ADMIN_ROLE)
    void doNotUpdateWithNullData() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .put(REQ_MAPP_CATEGORIES + REQ_MAPP_ID, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoToJson(new CategoriesDTO())))
                .andExpect(status().isBadRequest());

        verify(categoriesService, times(0)).update(1L, new CategoriesDTO());
    }

    @Test
    @WithMockUser(roles = ADMIN_ROLE)
    void deleteWithAdminRole() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .delete(REQ_MAPP_CATEGORIES + REQ_MAPP_ID, 1))
                .andExpect(status().isOk());

        verify(categoriesService, times(1)).deleteCategory(1L);
    }

    @Test
    @WithMockUser(USER_ROLE)
    void doNotDeleteWithUserRole() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .delete(REQ_MAPP_CATEGORIES + REQ_MAPP_ID, 1))
                .andExpect(status().isForbidden());

        verify(categoriesService, times(0)).deleteCategory(1L);
    }

    @Test
    void doNotDeleteWithoutRole() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .delete(REQ_MAPP_CATEGORIES + REQ_MAPP_ID, 1))
                .andExpect(status().isForbidden());

        verify(categoriesService, times(0)).deleteCategory(1L);
    }

    @Test
    @WithMockUser(roles = ADMIN_ROLE)
    void doNotDeleteWithNonExistentId() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .delete(REQ_MAPP_CATEGORIES + REQ_MAPP_ID, 2))
                .andExpect(status().isBadRequest());

        verify(categoriesService, times(1)).deleteCategory(2L);
    }

    private String dtoToJson(CategoriesDTO categoriesDTO) throws IOException {
        return json.write(categoriesDTO).getJson();
    }
}
