package com.alkemy.ong.services.serviceImpl;

import com.alkemy.ong.dto.MemberDTO;
import com.alkemy.ong.entities.Members;
import com.alkemy.ong.repositories.MembersRepository;
import com.alkemy.ong.utility.ConvertUtil;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MembersServices {
    
    @Autowired
    private MembersRepository membersRepository;
    
    @Transactional
    public void createMember(MemberDTO memberDTO){
        Members member = ConvertUtil.memberToEntity(memberDTO);
        
        member.setTimestamp(Timestamp.from(Instant.now()));
        member.setSoftDelete(false);
        
        membersRepository.save(member);
    }
    
    public Optional<Members> getMember(Long id){ 
        return membersRepository.findById(id);
    }
    
    public List<MemberDTO> getMembers(){
        List<Members> members = membersRepository.findAll();
        List<MemberDTO> membersList = new ArrayList<>();
        
        for (Members member : members) {
            membersList.add(ConvertUtil.memberToDTO(member));
        }
        
        return membersList;
    }
    
    @Transactional
    public void deleteMember(Long id){
        Members member = getMember(id).get();
        member.setSoftDelete(true);
        membersRepository.save(member);   
    }    
    
}
