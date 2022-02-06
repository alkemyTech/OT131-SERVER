package com.alkemy.ong.service;

import com.alkemy.ong.dto.MemberDTO;
import com.alkemy.ong.exception.ParamNotFoundException;
import com.alkemy.ong.model.Members;
import com.alkemy.ong.mapper.MembersMapper;
import com.alkemy.ong.repository.MembersRepository;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.alkemy.ong.util.Constants.*;

@Service
public class MembersServicesImpl implements MembersService{
    
    @Autowired
    private MembersRepository membersRepository;
    
    @Override
    public void createMember(MemberDTO memberDTO){
        Members member = MembersMapper.memberToEntity(memberDTO);
        
        member.setTimestamp(Timestamp.from(Instant.now()));
        member.setIsActive(false);
        
        membersRepository.save(member);
    }
    
    @Override
    public Members getMember(Long id){ 
        return membersRepository.findById(id).get();
    }
    @Override
    public List<MemberDTO> getMembers(){
        List<Members> members = membersRepository.findAll();
        List<MemberDTO> membersList = new ArrayList<>();
        
        for (Members member : members) {
            membersList.add(MembersMapper.memberToDTO(member));
        }
        
        return membersList;
    }
    
    @Override
    public void deleteMember(Long id){
        Optional<Members> member = membersRepository.findById(id);
        if(member.isEmpty()) {
        	throw new ParamNotFoundException(ENTITY_NOT_FOUND);
        }
        member.get().setIsActive(false);
        membersRepository.save(member.get());   
    }    
    
}
