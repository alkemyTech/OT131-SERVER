package com.alkemy.ong.controller;

import com.alkemy.ong.dto.NewsDTO;
import com.alkemy.ong.service.NewsService;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
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
                .name("name")
                .content("content")
                .image("image")
                .idCategory(1L)
                .build();

        when(newsService.findById(1L)).thenReturn(newsDTO);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void doNotSaveWithNullData() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/news")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoToJson(new NewsDTO())))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        verify(newsService, times(0)).save(newsDTO);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void saveWithadminRole() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/news")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoToJson(newsDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        verify(newsService, times(1)).save(newsDTO);
    }

    @Test
    @WithMockUser(roles = "USER")
    public void doNotSaveWithUserRole() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/news")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoToJson(newsDTO)))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN") // ToDo -> chequear permisos de rutas
    public void getByIdWhitAdminRole() throws Exception {
        
        this.mockMvc.perform(get("/news/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(newsService, times(1)).findById(1L);
    }

    private String dtoToJson(NewsDTO dto) throws IOException {
        return json.write(dto).getJson();
    }
}
