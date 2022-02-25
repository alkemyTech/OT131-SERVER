package com.alkemy.ong.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.alkemy.ong.dto.MemberDTO;
import com.alkemy.ong.model.Members;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MembersMapper {

    private ModelMapper mapper = new ModelMapper();

    public static MemberDTO memberToDTO(Members member) {
        return new MemberDTO(member.getIdMember(),
                member.getName(),
                member.getFacebookUrl(),
                member.getInstagramUrl(),
                member.getLinkedinUrl(),
                member.getImage(),
                member.getDescription());
    }

    public static Members memberToEntity(MemberDTO dto) {
        return new Members(dto.getName(),
                dto.getFacebookUrl(),
                dto.getInstagramUrl(),
                dto.getLinkedinUrl(),
                dto.getImage(),
                dto.getDescription());
    }

    public List<MemberDTO> listMembersToDto(List<Members> list) {
        return list.stream().map(members -> mapper.map(members, MemberDTO.class))
                .collect(Collectors.toList());
    }
}
