package com.alkemy.ong.service;

import com.alkemy.ong.dto.ContactsDTO;
import com.alkemy.ong.exception.AlreadyExistsException;
import com.alkemy.ong.model.Contacts;
import com.alkemy.ong.repository.ContactsRepository;
import static com.alkemy.ong.util.Constants.ERR_CONTACT_ALREADY_EXISTS;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContactsServiceImpl implements ContactsService {

    @Autowired
    private ContactsRepository contactRepository;

    private ModelMapper mapper = new ModelMapper();

    @Override
    @Transactional
    public ContactsDTO save(ContactsDTO contact) {
        if (contactRepository.findByEmail(contact.getEmail()).isPresent()) {
            throw new AlreadyExistsException(ERR_CONTACT_ALREADY_EXISTS);
        }

        Contacts newContact = mapper.map(contact, Contacts.class);
        Contacts contactSaved = contactRepository.save(newContact);

        return mapper.map(contactSaved, ContactsDTO.class);
    }

}
