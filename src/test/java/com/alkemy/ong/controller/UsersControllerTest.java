package com.alkemy.ong.controller;

import static com.alkemy.ong.util.Constants.REQ_MAPP_CLASS_USER;
import static com.alkemy.ong.util.Constants.REQ_MAPP_POST_LOGIN_USER;
import static com.alkemy.ong.util.Constants.REQ_MAPP_POST_REGISTER_USER;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alkemy.ong.dto.LoginUsersDTO;
import com.alkemy.ong.dto.NewUsersDTO;
import com.alkemy.ong.dto.UsersDtoResponse;
import com.alkemy.ong.model.Roles;
import com.alkemy.ong.service.UsersServiceImpl;
import com.alkemy.ong.util.JWT;
import com.alkemy.ong.util.RoleName;

import org.apache.catalina.User;
import org.hibernate.annotations.SourceType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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
    @Autowired
    private JacksonTester<UsersDtoResponse> jsonRes;
    private UsersDtoResponse usersDtoResponse;
    private LoginUsersDTO loginUsersDTO;
    private NewUsersDTO newUsersDTO;
    private JWT jwt;

    @BeforeEach
    public void setUp() {

        this.newUsersDTO = NewUsersDTO.builder()
                .email("admin2@ong.com")
                .firstName("admin")
                .lastName("admin")
                .password("12345678")
                .build();

        this.loginUsersDTO = LoginUsersDTO.builder()
                .email(newUsersDTO.getEmail())
                .password(newUsersDTO.getPassword())
                .build(); 
        
        try {
            this.usersDtoResponse = (usersService.login(this.loginUsersDTO));
        } catch (Exception e) {                        
            e.printStackTrace();
        }
        
                                    
    }

    private String dtoLoginToJson(LoginUsersDTO dto) throws Exception {
        return jsonLogin.write(dto).getJson();
    }

    private String dtoNewToJson(NewUsersDTO dto) throws Exception {
        return jsonNew.write(dto).getJson();
    }

    private String dtoResponseToJson (UsersDtoResponse dto) throws Exception  {
        System.out.println(dto);
        return jsonRes.write(dto).getJson();
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
                .content(dtoNewToJson(NewUsersDTO.builder().build())))
                .andExpect(status().isBadRequest());
        verify(usersService, times(0)).save(newUsersDTO);
    }

    @Test
    void testLogin () throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
        .post(REQ_MAPP_CLASS_USER + REQ_MAPP_POST_LOGIN_USER)
        .contentType(MediaType.APPLICATION_JSON)
        .content(dtoResponseToJson(this.usersDtoResponse)))
        .andExpect(status().isOk());        
    }

}
