package com.alkemy.ong.service;

import com.alkemy.ong.dto.ContactsDTO;
import com.alkemy.ong.dto.NewContactsDTO;
import java.util.List;

public interface ContactsService {

    ContactsDTO save(NewContactsDTO contact);

    void delete(Long id);

    List<ContactsDTO> findByAll();
}
