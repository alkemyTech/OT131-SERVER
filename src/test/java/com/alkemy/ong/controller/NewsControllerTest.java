package com.alkemy.ong.controller;

import com.alkemy.ong.dto.NewsDTO;
import com.alkemy.ong.exception.ParamNotFoundException;
import com.alkemy.ong.service.NewsService;
import static com.alkemy.ong.util.Constants.*;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class NewsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NewsService newsService;

    @Autowired
    private JacksonTester<NewsDTO> json;

    private NewsDTO newsDTO;

    @BeforeEach
    public void setUp() {
        this.newsDTO = NewsDTO.builder()
                .name(FOO_STRING)
                .content(FOO_STRING)
                .image(FOO_STRING)
                .idCategory(1L)
                .build();

        when(newsService.findById(2L)).thenThrow(new ParamNotFoundException(EMPTY_STRING));
        when(newsService.update(2L, newsDTO)).thenThrow(new ParamNotFoundException(EMPTY_STRING));
        doThrow(new ParamNotFoundException(EMPTY_STRING)).when(newsService).deleteNew(2L);
        when(newsService.getAll(2)).thenThrow(new ParamNotFoundException(EMPTY_STRING));
    }

    @Test
    @WithMockUser(roles = ADMIN_ROLE)
    public void doNotSaveWithNullData() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .post(REQ_MAPP_NEWS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoToJson(new NewsDTO())))
                .andExpect(status().isBadRequest());

        verify(newsService, times(0)).save(newsDTO);
    }

    @Test
    @WithMockUser(roles = ADMIN_ROLE)
    public void saveWithadminRole() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .post(REQ_MAPP_NEWS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoToJson(newsDTO)))
                .andExpect(status().isCreated());

        verify(newsService, times(1)).save(newsDTO);
    }

    @Test
    @WithMockUser(roles = USER_ROLE)
    public void doNotSaveWithUserRole() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .post(REQ_MAPP_NEWS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoToJson(newsDTO)))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = ADMIN_ROLE)
    public void getByIdWhitAdminRole() throws Exception {

        this.mockMvc.perform(get(REQ_MAPP_NEWS + REQ_MAPP_ID, 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(newsService, times(1)).findById(1L);
    }

    @Test
    public void getByIdWhitoutRole() throws Exception {

        this.mockMvc.perform(get(REQ_MAPP_NEWS + REQ_MAPP_ID, 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());

        verify(newsService, times(0)).findById(1L);
    }

    @Test
    @WithMockUser(roles = ADMIN_ROLE)
    public void getByNonexistentId() throws Exception {

        this.mockMvc.perform(get(REQ_MAPP_NEWS + REQ_MAPP_ID, 2)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(newsService, times(1)).findById(2L);
    }

    @Test
    @WithMockUser(roles = ADMIN_ROLE)
    public void updateWithAdminRole() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .put(REQ_MAPP_NEWS + REQ_MAPP_ID, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoToJson(newsDTO)))
                .andExpect(status().isOk());

        verify(newsService, times(1)).update(1L, newsDTO);
    }

    @Test
    @WithMockUser(roles = USER_ROLE)
    public void doNotUpdateWithUserRole() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .put(REQ_MAPP_NEWS + REQ_MAPP_ID, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoToJson(newsDTO)))
                .andExpect(status().isForbidden());

        verify(newsService, times(0)).update(1L, newsDTO);
    }

    @Test
    @WithMockUser(roles = ADMIN_ROLE)
    public void doNotUpdateWithNonexistentId() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .put(REQ_MAPP_NEWS + REQ_MAPP_ID, 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoToJson(newsDTO)))
                .andExpect(status().isBadRequest());

        verify(newsService, times(1)).update(2L, newsDTO);
    }

    @Test
    @WithMockUser(roles = ADMIN_ROLE)
    public void doNotUpdateWithNullData() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .put(REQ_MAPP_NEWS + REQ_MAPP_ID, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoToJson(new NewsDTO())))
                .andExpect(status().isBadRequest());

        verify(newsService, times(0)).update(1L, new NewsDTO());
    }

    @Test
    @WithMockUser(roles = ADMIN_ROLE)
    public void deleteWithAdminRole() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .delete(REQ_MAPP_NEWS + REQ_MAPP_ID, 1))
                .andExpect(status().isOk());

        verify(newsService, times(1)).deleteNew(1L);
    }

    @Test
    @WithMockUser(roles = USER_ROLE)
    public void deleteWithUserRole() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .delete(REQ_MAPP_NEWS + REQ_MAPP_ID, 1))
                .andExpect(status().isForbidden());

        verify(newsService, times(0)).deleteNew(1L);
    }

    @Test
    @WithMockUser(roles = ADMIN_ROLE)
    public void deleteWithNonexistentId() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .delete(REQ_MAPP_NEWS + REQ_MAPP_ID, 2))
                .andExpect(status().isBadRequest());

        verify(newsService, times(1)).deleteNew(2L);
    }

    @Test
    @WithMockUser(roles = ADMIN_ROLE)
    public void getAllWithAdminRole() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .get(REQ_MAPP_NEWS + URL_PAGE_TEST, 1))
                .andExpect(status().isOk());

        verify(newsService, times(1)).getAll(1);
    }

    @Test
    @WithMockUser(roles = USER_ROLE)
    public void getAllWithUserRole() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .get(REQ_MAPP_NEWS + URL_PAGE_TEST, 1))
                .andExpect(status().isOk());

        verify(newsService, times(1)).getAll(1);
    }

    @Test
    public void getAllWithoutRole() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .get(REQ_MAPP_NEWS + URL_PAGE_TEST, 1))
                .andExpect(status().isForbidden());

        verify(newsService, times(0)).getAll(1);
    }

    @Test
    @WithMockUser(roles = ADMIN_ROLE)
    public void getAllWithNonexistentId() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .get(REQ_MAPP_NEWS + URL_PAGE_TEST, 2))
                .andExpect(status().isBadRequest());

        verify(newsService, times(1)).getAll(2);
    }

    private String dtoToJson(NewsDTO dto) throws IOException {
        return json.write(dto).getJson();
    }
}
