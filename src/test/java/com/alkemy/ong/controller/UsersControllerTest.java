package com.alkemy.ong.controller;

import com.alkemy.ong.dto.LoginUsersDTO;
import com.alkemy.ong.dto.NewUsersDTO;
import com.alkemy.ong.service.UsersService;
import com.alkemy.ong.service.UsersServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static com.alkemy.ong.util.Constants.*;

import static org.mockito.Mockito.*;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class UsersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean    
    private UsersServiceImpl usersService;

    @Autowired
    private JacksonTester<LoginUsersDTO> jsonLogin;
    
    @Autowired
    private JacksonTester<NewUsersDTO> jsonNew; 

    private LoginUsersDTO loginUsersDTO;

    private NewUsersDTO newUsersDTO;


    @BeforeEach
    public void setUp() {

        this.loginUsersDTO = loginUsersDTO.builder()
        .email("test@ong.com")
        .password("12345678")
        .build();

        this.newUsersDTO = newUsersDTO.builder()
        .email("test@ong.com")
        .firstName("admin")
        .lastName("admin")
        .password("12345678")
        .build();
    }

    
    private String dtoLoginToJson(LoginUsersDTO dto) throws Exception {
        return jsonLogin.write(dto).getJson();
    }

    private String dtoNewToJson(NewUsersDTO dto) throws Exception {
        return jsonNew.write(dto).getJson();
    }

    @Test
    void testRegister() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
        .post(REQ_MAPP_CLASS_USER + REQ_MAPP_POST_REGISTER_USER)
        .contentType(MediaType.APPLICATION_JSON)
        .content(dtoNewToJson(newUsersDTO)))
        .andExpect(status().isCreated());       

    }        

    @Test
    void doNotSaveWithNullData() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post(REQ_MAPP_CLASS_USER + REQ_MAPP_POST_REGISTER_USER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dtoNewToJson(new NewUsersDTO())))
                        .andExpect(status().isBadRequest());
        verify(usersService, times(0)).save(newUsersDTO);
    }

    @Test
    void testAuthMe() {

    }

    @Test
    void testListUsers() {

    }

    
}
