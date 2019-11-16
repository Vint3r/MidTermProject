package com.skilldistillery.goodwork.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

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

}