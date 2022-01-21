package com.alkemy.ong.utility;

import com.alkemy.ong.dto.MemberDTO;
import com.alkemy.ong.entities.Members;

public class ConvertUtil {
    
    public static MemberDTO memberToDTO(Members member){
        MemberDTO dto = new MemberDTO();
        
        dto.setName(member.getName());
        dto.setFacebookUrl(member.getFacebookUrl());
        dto.setInstagramUrl(member.getInstagramUrl());
        dto.setLinkedinUrl(member.getLinkedinUrl());
        dto.setImage(member.getImage());
        dto.setDescription(member.getDescription());
        return dto;
    }
    
    public static Members memberToEntity(MemberDTO dto){
        Members member = new Members();
        
        member.setName(dto.getName());
        member.setFacebookUrl(dto.getFacebookUrl());
        member.setInstagramUrl(dto.getInstagramUrl());
        member.setLinkedinUrl(dto.getLinkedinUrl());
        member.setImage(dto.getImage());
        member.setDescription(dto.getDescription());
        
        return member;
    }
}
