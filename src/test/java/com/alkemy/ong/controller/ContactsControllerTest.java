package com.alkemy.ong.controller;

import com.alkemy.ong.dto.NewContactsDTO;
import com.alkemy.ong.exception.ParamNotFoundException;
import com.alkemy.ong.service.ContactsService;
import static com.alkemy.ong.util.Constants.*;
import java.io.IOException;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class ContactsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContactsService contactsService;

    @Autowired
    private JacksonTester<NewContactsDTO> json;

    private NewContactsDTO newContactsDTO;

    @BeforeEach
    public void setUp() {
        this.newContactsDTO = NewContactsDTO.builder()
                .name(FOO_STRING)
                .phone(1L)
                .email(EMAIL_TEST)
                .message(FOO_STRING)
                .build();
        doThrow(new ParamNotFoundException(EMPTY_STRING)).when(contactsService).delete(2L);
    }

    @Test
    @WithMockUser(roles = {ADMIN_ROLE, USER_ROLE})
    public void saveContactOk() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .post(REQ_MAPP_CONTACTS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoToJson(newContactsDTO)))
                .andExpect(status().isCreated());
        verify(contactsService, times(1)).save(newContactsDTO);
    }

    @Test
    @WithMockUser(roles = {ADMIN_ROLE, USER_ROLE})
    public void doNotSaveContactWithNullData() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .post(REQ_MAPP_CONTACTS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoToJson(new NewContactsDTO())))
                .andExpect(status().isBadRequest());
        verify(contactsService, times(0)).save(newContactsDTO);
    }

    @Test
    @WithMockUser(roles = {ADMIN_ROLE, USER_ROLE})
    public void listContact() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get(REQ_MAPP_CONTACTS))
                .andExpect(status().isOk());
        verify(contactsService, times(1)).findByAll();
    }

    @Test
    public void listContactWithoutRole() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get(REQ_MAPP_CONTACTS))
                .andExpect(status().isForbidden());
        verify(contactsService, times(0)).findByAll();
    }

    @Test
    @WithMockUser(roles = {ADMIN_ROLE, USER_ROLE})
    public void deleteContact() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .delete(REQ_MAPP_CONTACTS + REQ_MAPP_ID, 1))
                .andExpect(status().isOk());
        verify(contactsService, times(1)).delete(1L);
    }

    @Test
    @WithMockUser(roles = {ADMIN_ROLE, USER_ROLE})
    public void deleteContactWithNonExistentId() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .delete(REQ_MAPP_CONTACTS + REQ_MAPP_ID, 2))
                .andExpect(status().isBadRequest());
        verify(contactsService, times(1)).delete(2L);
    }

    @Test
    public void deleteContactWithoutRole() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .delete(REQ_MAPP_CONTACTS + REQ_MAPP_ID, 1))
                .andExpect(status().isForbidden());
        verify(contactsService, times(0)).delete(1L);
    }

    private String dtoToJson(NewContactsDTO dto) throws IOException {
        return json.write(dto).getJson();
    }

}
