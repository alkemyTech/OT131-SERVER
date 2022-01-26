package com.alkemy.ong.services.mapper;

import com.alkemy.ong.dto.MemberDTO;
import com.alkemy.ong.model.Members;

public class MembersMapper {
    
    public static MemberDTO memberToDTO(Members member){
        return new MemberDTO(member.getName(), 
                        member.getFacebookUrl(), 
                        member.getInstagramUrl(), 
                        member.getLinkedinUrl(), 
                        member.getImage(), 
                        member.getDescription());
    }
    
    public static Members memberToEntity(MemberDTO dto){
        return new Members(dto.getName(), 
                        dto.getFacebookUrl(), 
                        dto.getInstagramUrl(), 
                        dto.getLinkedinUrl(), 
                        dto.getImage(), 
                        dto.getDescription());
    }
}
