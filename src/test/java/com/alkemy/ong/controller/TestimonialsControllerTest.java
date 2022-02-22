package com.alkemy.ong.controller;


import com.alkemy.ong.dto.TestimonialsAddNewDto;
import com.alkemy.ong.dto.TestimonialsDto;
import com.alkemy.ong.service.TestimonialsService;
import static com.alkemy.ong.util.Constants.ADMIN_ROLE;
import static com.alkemy.ong.util.Constants.REQ_MAPP_ID;
import static com.alkemy.ong.util.Constants.REQ_MAPP_TESTIMONIALS;
import static com.alkemy.ong.util.Constants.URL_PAGE_TEST;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.web.context.WebApplicationContext;

@AutoConfigureJsonTesters
@AutoConfigureMockMvc
@SpringBootTest
public class TestimonialsControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<TestimonialsAddNewDto> jsonAdd;

    @Autowired
    private JacksonTester<TestimonialsDto> jsonGet;

    @MockBean
    TestimonialsService testimonialsService;

    private TestimonialsAddNewDto testimonialsAddNewDto;
    private TestimonialsDto testimonialsDto;
    private final Long id = 1L;

    @BeforeEach
    public void init() {

        this.testimonialsAddNewDto = new TestimonialsAddNewDto();
        this.testimonialsAddNewDto.setName("Name");
        this.testimonialsAddNewDto.setImage("Name.jpg");
        this.testimonialsAddNewDto.setContent("NameContent");

        this.testimonialsDto = new TestimonialsDto();
        this.testimonialsDto.setId(this.id);
        this.testimonialsDto.setName(this.testimonialsAddNewDto.getName());
        this.testimonialsDto.setImage(this.testimonialsAddNewDto.getImage());
        this.testimonialsDto.setContent(this.testimonialsAddNewDto.getContent());
    }

    @Test
    @WithMockUser(roles = ADMIN_ROLE)
    public void addNewTestimonials() throws Exception {
        Mockito.when(testimonialsService.save(testimonialsAddNewDto))
                .thenReturn(testimonialsDto);

        RequestBuilder request = MockMvcRequestBuilders
                .post(REQ_MAPP_TESTIMONIALS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoAddToJson(this.testimonialsAddNewDto));

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id").exists())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @WithMockUser(roles = ADMIN_ROLE)
    public void updateTestimonials() throws Exception {
        Mockito.when(this.testimonialsService.updateTestimonails(this.testimonialsDto, this.id))
                .thenReturn(this.testimonialsDto);

        RequestBuilder request = MockMvcRequestBuilders
                .put(REQ_MAPP_TESTIMONIALS + REQ_MAPP_ID , this.id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoGetToJsson(this.testimonialsDto));

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        verify(this.testimonialsService, times(1)).updateTestimonails(this.testimonialsDto, this.id);
    }

    @Test
    @WithMockUser(roles = ADMIN_ROLE)
    public void deleteTestimonials() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .delete(REQ_MAPP_TESTIMONIALS + REQ_MAPP_ID, this.id);

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    @WithMockUser(roles = ADMIN_ROLE)
    public void getPage() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get(REQ_MAPP_TESTIMONIALS + URL_PAGE_TEST, 1);
//                .param("page", "1");

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    
    
    private String dtoAddToJson(TestimonialsAddNewDto dto) throws IOException {
        return this.jsonAdd.write(dto).getJson();
    }

    private String dtoGetToJsson(TestimonialsDto dto) throws IOException {
        return this.jsonGet.write(dto).getJson();
    }
}

