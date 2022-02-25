package com.alkemy.ong.controller;

import static com.alkemy.ong.util.Constants.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.alkemy.ong.dto.LoginUsersDTO;
import com.alkemy.ong.dto.NewUsersDTO;
import com.alkemy.ong.dto.UsersDtoResponse;
import com.alkemy.ong.dto.UsersOkDto;

import com.alkemy.ong.service.UsersServiceImpl;
import com.alkemy.ong.util.JWT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@ExtendWith(SpringExtension.class)
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

    @BeforeEach
    public void setUp() throws Exception {

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

        this.usersDtoResponse = UsersDtoResponse.builder()
                .email(newUsersDTO.getEmail())
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
                .content(dtoNewToJson(NewUsersDTO.builder().build())))
                .andExpect(status().isBadRequest());
        verify(usersService, times(0)).save(newUsersDTO);
    }

    @Test
    void testLogin() throws Exception {
        when(usersService.login(loginUsersDTO)).thenReturn(this.usersDtoResponse);
        this.mockMvc.perform(MockMvcRequestBuilders
                .post(REQ_MAPP_CLASS_USER + REQ_MAPP_POST_LOGIN_USER)
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoLoginToJson(this.loginUsersDTO)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testUpdateAdmin() throws Exception {
        when(usersService.update(1L, newUsersDTO)).thenReturn(this.usersDtoResponse);
        this.mockMvc.perform(MockMvcRequestBuilders
                .put(REQ_MAPP_CLASS_USER + REQ_MAPP_DELETE_LOGIN_USER, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoNewToJson(newUsersDTO)))
                .andExpect(status().isOk());

    }

    @Test
    void testUpdateWithoutRole() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .put(REQ_MAPP_CLASS_USER + REQ_MAPP_DELETE_LOGIN_USER, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoNewToJson(newUsersDTO)))
                .andExpect(status().isForbidden());

    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testUpdateWithNullData() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .put(REQ_MAPP_CLASS_USER + REQ_MAPP_DELETE_LOGIN_USER, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoNewToJson(NewUsersDTO.builder().build())))
                .andExpect(status().isBadRequest());
        verify(usersService, times(0)).save(newUsersDTO);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testDeleteWithRoleAdmin() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .delete(REQ_MAPP_CLASS_USER + REQ_MAPP_DELETE_LOGIN_USER, 1))
                .andExpect(status().isOk());
        verify(usersService, times(1)).delete(1L);

    }

    @Test
    void testDeleteWithoutRoleAdmin() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .delete(REQ_MAPP_CLASS_USER + REQ_MAPP_DELETE_LOGIN_USER, 1))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnListOfUsers() throws Exception {
        List<UsersOkDto> listUser = new ArrayList();
        when(usersService.listUsers()).thenReturn(listUser);
        this.mockMvc.perform(MockMvcRequestBuilders
                .get(REQ_MAPP_CLASS_USER + REQ_MAPP_GET_LIST_USER))
                .andExpect(status().isOk());
        verify(usersService, times(1)).listUsers();
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldNotReturnListOfUsers() throws Exception {
        List<UsersOkDto> listUser = new ArrayList();
        when(usersService.listUsers()).thenReturn(listUser);
        this.mockMvc.perform(MockMvcRequestBuilders
                .get(REQ_MAPP_CLASS_USER + REQ_MAPP_GET_LIST_USER))
                .andExpect(status().isForbidden());
        verify(usersService, times(0)).listUsers();
    }
}
