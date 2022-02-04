package com.alkemy.ong.service;

import com.alkemy.ong.dto.ContactsDTO;
import com.alkemy.ong.dto.NewContactsDTO;
import com.alkemy.ong.exception.AlreadyExistsException;
import com.alkemy.ong.exception.ParamNotFoundException;
import com.alkemy.ong.model.Contacts;
import com.alkemy.ong.repository.ContactsRepository;

import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.alkemy.ong.util.Constants.*;

@Service
public class ContactsServiceImpl implements ContactsService {

    @Autowired
    private ContactsRepository contactRepository;

    @Autowired
    private EmailServiceImp emailService;

    private ModelMapper mapper = new ModelMapper();

    @Override
    @Transactional
    public ContactsDTO save(NewContactsDTO contact) {
        if (contactRepository.findByEmail(contact.getEmail()).isPresent()) {
            throw new AlreadyExistsException(ERR_CONTACT_ALREADY_EXISTS);
        }

        Contacts newContact = mapper.map(contact, Contacts.class);
        Contacts contactSaved = contactRepository.save(newContact);

        emailService.sendContactConfirmation(MAIL_ONG, contactSaved.getEmail());

        return mapper.map(contactSaved, ContactsDTO.class);
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
        Optional<Contacts> result = contactRepository.findById(id);
        if (!result.isPresent()) {
            throw new ParamNotFoundException(ERR_CONTACT_NOT_FOUND);
        }
        
        Contacts contact = result.get();
        contact.setActive(false);
        contactRepository.save(contact);
    }
}
