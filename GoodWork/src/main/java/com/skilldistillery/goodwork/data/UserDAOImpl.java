package com.skilldistillery.goodwork.data;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.skilldistillery.goodwork.entities.Event;
import com.skilldistillery.goodwork.entities.Organization;
import com.skilldistillery.goodwork.entities.User;
import com.skilldistillery.goodwork.entities.UserEvent;
import com.skilldistillery.goodwork.entities.UserEventId;

@Transactional
@Service
public class UserDAOImpl implements UserDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public User getUserById(int id) {
		User user = em.find(User.class, id);
		user.getAttendedEvents().size();
		user.getHostedEvents().size();
		user.getOrgs().size();		//forces java to remember the new list in the session, re-populates the list.
		user.getMessBoards().size();
		return user;
	}
	
	public User updateUser(User user) {
		User upUser = em.find(User.class, user.getId());
		upUser.setBio(user.getBio());
		upUser.setEmail(user.getEmail());
		upUser.setFirstName(user.getFirstName());
		upUser.setLastName(user.getLastName());
		upUser.setPassword(user.getPassword());
		upUser.setPhotoURL(user.getPhotoURL());
		upUser.setUserName(user.getUserName());
		em.persist(upUser);
		em.flush();
		return upUser;
	}

	@Override
	public boolean deleteUser(int id) {
		User user = em.find(User.class, id);
		boolean success = false;

		if (!user.equals(null)) {
			user.setActive(false);
			em.persist(user);
			em.flush();
		}
		success = user.getActive();
		return success;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users = null;
		String sql = "SELECT user FROM User user ORDER BY user.firstName, user.lastName";
		users = em.createQuery(sql, User.class).getResultList();
		return users;
	}
	@Override
	public List<User> getActiveUsers() {
		List<User> users = null;
		String sql = "SELECT user FROM User user WHERE user.active = true";
		users = em.createQuery(sql, User.class).getResultList();
		return users;
	}

	@Override
	public User getUserByEmail(String email) {
		User u = null;
		String sql = "SELECT user FROM User user WHERE user.email = :userMail";
		List<User> user = em.createQuery(sql, User.class).setParameter("userMail", email).getResultList();
		if (user != null && user.size() > 0) {
			u = user.get(0);
		}
		return u;
	}

	@Override
	public List<User> getAllUsersByKeyword(String keyword) {
		List<User> users = null;
		String sql = "SELECT user FROM User user WHERE user.userName LIKE :keyword OR user.firstName LIKE :keyword OR user.lastName LIKE :keyword";
		
		users = em.createQuery(sql, User.class).setParameter("keyword", "%" + keyword + "%").getResultList();
		
		return users;
	}

	@Override
	public boolean signedUpForEvent(Event event, User user) {
		UserEventId signedUp = new UserEventId(user.getId(), event.getId());
		UserEvent eventSignedUp = new UserEvent();
		user = em.find(User.class, user.getId());
		event = em.find(Event.class, event.getId());
		
		eventSignedUp.setId(signedUp);
		eventSignedUp.setActive(true);
		eventSignedUp.setAttended(true);
		eventSignedUp.setDateSignedUp(LocalDate.now());
		eventSignedUp.setRole("Helper");
//		eventSignedUp.setEvent(event);
//		eventSignedUp.setUser(user);
		
		event.addUserEvent(eventSignedUp);
		user.addUserEvent(eventSignedUp);
		em.persist(eventSignedUp);
		em.persist(user);
		em.persist(event);
		em.flush();
		
		if(user.getAttendedEvents().contains(eventSignedUp) && event.getUsers().contains(eventSignedUp)) {
			return true;
		}
		return false;
	}
	
	public boolean unRegisterFromEvent(User user, Event event) {
		UserEventId ueId = new UserEventId(user.getId(), event.getId());
		UserEvent unRegister = em.find(UserEvent.class, ueId);
		user = em.find(User.class, user.getId());
		event = em.find(Event.class, event.getId());
		
		user.removeUserEvent(unRegister);
		event.removeUserEvent(unRegister);
		em.remove(unRegister);
		
		em.persist(user);
		em.persist(event);
		em.flush();
		
		if(user.getAttendedEvents().contains(unRegister) && event.getUsers().contains(unRegister)) {
			return false;
		}
		return true;
	}
	
	public boolean signedUpForOrg(Organization org, User user) {
		user = em.find(User.class, user.getId());
		org = em.find(Organization.class, org.getId());
		
		org.addUser(user);
		user.addOrganization(org);
		
		em.persist(user);
		em.persist(org);
		em.flush();
		
		if(user.getOrgs().contains(org) && org.getUsers().contains(user)) {
			return true;
		}
		return false;
	}
	
	public boolean unRegisterFromOrg(User user, Organization org) {
		user = em.find(User.class, user.getId());
		org = em.find(Organization.class, org.getId());
		
		org.removeUser(user);
		user.removeOrganization(org);
		
		em.persist(user);
		em.persist(org);
		em.flush();
		
		if(user.getOrgs().contains(org) && org.getUsers().contains(user)) {
			return false;
		}
		return true;
	}
	
	public UserEvent getUserEvent(UserEventId ueId) {
		UserEvent ue = em.find(UserEvent.class, ueId);
		return ue;
	}

}
