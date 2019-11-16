package com.skilldistillery.goodwork.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.skilldistillery.goodwork.entities.Organization;


@Service
@Transactional
public class OrgDAOImpl implements OrgDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Organization> getAll() {
		List<Organization> organizationList = em.createQuery("select o from Organization o where active= 1", Organization.class)
				.getResultList();
		em.flush();
		return organizationList;
	}

	@Override
	public Organization findById(int id) {
		return em.find(Organization.class, id);
	}

	@Override
	public Organization addNewOrg(Organization org) {
		org.setActive(true);
		em.persist(org);
		em.flush();
		return org;

	}
	
	@Override
	public boolean disableOrganization(int id) {
		if (em.find(Organization.class, id) != null) {
			Organization disableOrg = em.find(Organization.class, id);
			disableOrg.setActive(false);
			

			return true;
		} else

			return false;
	}
	
	@Override
	public Organization updateOrganization(Organization orgData) {
		Organization updateData = em.find(Organization.class, orgData.getId());
		updateData.getLocation().setAddress(orgData.getLocation().getAddress());
		updateData.getLocation().setAddress2(orgData.getLocation().getAddress2());
		updateData.getLocation().setCity(orgData.getLocation().getCity());
		updateData.getLocation().setState(orgData.getLocation().getState());
		updateData.getLocation().setZipCode(orgData.getLocation().getZipCode());
		updateData.setOrgName(orgData.getOrgName());
		updateData.setOrgType(orgData.getOrgType());
		updateData.setOrgNum(orgData.getOrgNum());
		updateData.setWebsite(orgData.getWebsite());
//		updateData.setDescription(orgData.getDescription));
		return updateData;

	}


}
