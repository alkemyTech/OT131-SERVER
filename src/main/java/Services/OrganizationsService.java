package Services;

import java.util.List;


import com.alkemy.ong.dto.OrganizationsDTO;
import com.alkemy.ong.entities.Organizations;

public interface OrganizationsService {
	
	public Organizations saveOrganization(Organizations organization);
	public List<OrganizationsDTO> listOrganizations();
	public OrganizationsDTO publicDataOrganization(String name);

}
