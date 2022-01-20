package Services;

import java.util.List;


import com.alkemy.ong.dto.OrganizationDTO;
import com.alkemy.ong.entities.Organization;

public interface IOrganizationService {
	
	public Organization saveOrganization(Organization organization);
	public List<OrganizationDTO> listOrganizations();
	public OrganizationDTO publicDataOrganization(String name);

}
