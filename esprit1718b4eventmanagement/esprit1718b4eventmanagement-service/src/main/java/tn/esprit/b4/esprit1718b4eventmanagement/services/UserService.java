package tn.esprit.b4.esprit1718b4eventmanagement.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import javax.ejb.LocalBean;

import javax.persistence.Query;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.User;


/**
 * Session Bean implementation class UserService
 */
@Stateless
@LocalBean
public class UserService implements UserServiceRemote, UserServiceLocal {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public User add(User u) {
		entityManager.persist(u);
		entityManager.flush();
		return u;
	}

	@Override
	public User update(User u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override

	public Boolean remove(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override

	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override

	public User findById(Integer id) {
		return entityManager.find(User.class, id);
	}

	@Override

	public User findByLogin(String l) {
		User user = null;
		String jpql = "SELECT u FROM User u WHERE u.login = :param1";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("param1", l);
		try {
			user = (User) query.getSingleResult();
			System.out.println("User found " + user.getLogin());
			return user;
		} catch (Exception e) {
			System.err.println("User Not found");
		}

		return null;
	}

	@Override
	public void save(User entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(User entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User find(int entityID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User login(String login, String password) {
		// TODO Auto-generated method stub
		return null;
	}
}
