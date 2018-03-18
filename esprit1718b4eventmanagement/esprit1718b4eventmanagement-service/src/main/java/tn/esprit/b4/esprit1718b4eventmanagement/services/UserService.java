package tn.esprit.b4.esprit1718b4eventmanagement.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import javax.ejb.LocalBean;

import javax.persistence.Query;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.User;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.GenericDAO;


/**
 * Session Bean implementation class UserService
 */
@Stateless
public class UserService extends GenericDAO<User> implements UserServiceRemote, UserServiceLocal {

	
	@PersistenceContext
	private EntityManager em;
  public UserService() {
	// TODO Auto-generated constructor stub
	 super(User.class);
}
	

	

	@Override

	public User findByLogin(String l) {
		User user = null;
		String jpql = "SELECT u FROM User u WHERE u.login = :param1";
		Query query = em.createQuery(jpql);
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
	public User login(String login, String password) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public void update1(User u) {
		em.merge(u);
		
	}





}
