package com.alkemy.ong.service;

import com.alkemy.ong.dto.MemberDTO;
import com.alkemy.ong.dto.NewMemberDTO;
import com.alkemy.ong.dto.PagesDTO;
import com.alkemy.ong.model.Members;
import java.util.List;

public interface MembersService {

    public NewMemberDTO createMember(NewMemberDTO memberDTO);

    public Members getMember(Long id);

    public List<MemberDTO> getMembers();

    public void deleteMember(Long id);

    public NewMemberDTO updateMember(NewMemberDTO memberDTO, Long id);

    public PagesDTO<MemberDTO> getAll(Integer page);

}
