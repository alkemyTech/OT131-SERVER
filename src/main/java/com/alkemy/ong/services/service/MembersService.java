package com.alkemy.ong.services.service;

import com.alkemy.ong.dto.MemberDTO;
import com.alkemy.ong.model.Members;
import java.util.List;

public interface MembersService {
    
    public void createMember(MemberDTO memberDTO);
    public Members getMember(Long id);
    public List<MemberDTO> getMembers();
    public void deleteMember(Long id);
    
}
