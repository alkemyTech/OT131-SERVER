package com.alkemy.ong.service;

import com.alkemy.ong.dto.MemberDTO;
import com.alkemy.ong.dto.NewMemberDTO;
import com.alkemy.ong.exception.AlreadyExistsException;
import com.alkemy.ong.exception.ParamNotFoundException;
import com.alkemy.ong.model.Members;
import com.alkemy.ong.mapper.MembersMapper;
import com.alkemy.ong.repository.MembersRepository;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.alkemy.ong.util.Constants.*;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MembersServicesImpl implements MembersService{

    @Autowired
    private MembersRepository membersRepository;
    private ModelMapper mapper = new ModelMapper();

    @Override
    @Transactional
    public MemberDTO createMember(NewMemberDTO memberDTO) {

        if (!memberDTO.getFacebookUrl().isBlank()
                && membersRepository.findByFacebookUrl(memberDTO.getFacebookUrl()).isPresent()) {
            throw new AlreadyExistsException(ERR_FB_MEMBER_ALREADY_EXISTS);
        }

        if (!memberDTO.getInstagramUrl().isBlank()
                && membersRepository.findByInstagramUrl(memberDTO.getInstagramUrl()).isPresent()) {
            throw new AlreadyExistsException(ERR_IG_MEMBER_ALREADY_EXISTS);
        }

        if (!memberDTO.getLinkedinUrl().isBlank()
                && membersRepository.findByLinkedinUrl(memberDTO.getLinkedinUrl()).isPresent()) {
            throw new AlreadyExistsException(ERR_LI_MEMBER_ALREADY_EXISTS);
        }

        Members member = mapper.map(memberDTO, Members.class);
        Members memberSaved = membersRepository.save(member);
        MemberDTO response = mapper.map(memberSaved, MemberDTO.class);

        return response;
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
    public void deleteMember(Long id) {
        Optional<Members> member = membersRepository.findById(id);
        if(member.isEmpty()) {
            throw new ParamNotFoundException(ENTITY_NOT_FOUND);
        }
        if(!member.get().getIsActive()) {
            throw new ParamNotFoundException(ERR_MEMBER_ALREADY_REMOVED);
        }
        member.get().setIsActive(false);
        membersRepository.save(member.get());
    }

	@Override
	public NewMemberDTO updateMember(NewMemberDTO memberDTO,Long id) {
		Optional<Members> members =membersRepository.findById(id);
		if (!members.isPresent()) {
			throw new ParamNotFoundException (ENTITY_NOT_FOUND);
		}
		if (!memberDTO.getFacebookUrl().isBlank()
                && membersRepository.findByFacebookUrl(memberDTO.getFacebookUrl()).isPresent()) {
            throw new AlreadyExistsException(ERR_FB_MEMBER_ALREADY_EXISTS);
        }

        if (!memberDTO.getInstagramUrl().isBlank()
                && membersRepository.findByInstagramUrl(memberDTO.getInstagramUrl()).isPresent()) {
            throw new AlreadyExistsException(ERR_IG_MEMBER_ALREADY_EXISTS);
        }

        if (!memberDTO.getLinkedinUrl().isBlank()
                && membersRepository.findByLinkedinUrl(memberDTO.getLinkedinUrl()).isPresent()) {
            throw new AlreadyExistsException(ERR_LI_MEMBER_ALREADY_EXISTS);
        }

		members.get().setName(memberDTO.getName());
		members.get().setDescription(memberDTO.getDescription());
		members.get().setFacebookUrl(memberDTO.getFacebookUrl());
		members.get().setImage(memberDTO.getImage());
		members.get().setInstagramUrl(memberDTO.getInstagramUrl());
		members.get().setLinkedinUrl(memberDTO.getLinkedinUrl());
		membersRepository.save(members.get());
		return mapper.map(members.get(), NewMemberDTO.class);
	
	}

}
