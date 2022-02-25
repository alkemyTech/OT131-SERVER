package com.alkemy.ong.service;

import java.util.List;

import com.alkemy.ong.dto.OrganizationsAllDTO;
import com.alkemy.ong.dto.OrganizationsDTO;

public interface OrganizationsService {

    public OrganizationsAllDTO saveOrganization(OrganizationsAllDTO organization) throws Exception;

    public List<OrganizationsDTO> listOrganizations();

    public OrganizationsDTO publicDataOrganization(String name);

    public OrganizationsAllDTO updateDataOrganization(OrganizationsAllDTO entity, long id) throws Exception;

}
