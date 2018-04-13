package tn.esprit.b4.esprit1718b4eventmanagement.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import javax.ejb.LocalBean;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.MvtApprov;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Nomenclature;
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




	@Override
	public List<User> SearchLogin(String login) {
	TypedQuery<User> query
		=em.createQuery("select n from User n where n.login=:Login", User.class);
		query.setParameter("Login", login);
		List<User> user=query.getResultList();
		return user;
	
	}

	@Override
	public User userbyfstlstname(String login) {
	TypedQuery<User> query
		=em.createQuery("select n from User n where CONCAT(n.firstname,' ',n.lastname)=:Login", User.class);
		query.setParameter("Login", login);
		User user=query.getSingleResult();
		return user;
	
	}



	@Override
	public List<User> SearchFirstName(String login) {
	TypedQuery<User> query
		=em.createQuery("select n from User n where n.firstname=:Login", User.class);
		query.setParameter("Login", login);
		List<User> user=query.getResultList();
		return user;
	
	}
	@Override
	public List<User> SearchStatut(String sta) {
	TypedQuery<User> query
		=em.createQuery("select n from User n where n.statut=:Login", User.class);
		query.setParameter("Login", sta);
		List<User> user=query.getResultList();
		return user;
	
	}


	@Override
	public List<User> SearchMail(String login) {
	TypedQuery<User> query
		=em.createQuery("select n from User n where n.email=:Login", User.class);
		query.setParameter("Login", login);
		List<User> user=query.getResultList();
		return user;
	
	}

	

	//*************************Done By ONS****************************//
	
	@Override
	public List<User> DisplayUser() {

		TypedQuery<User> query=em.createQuery("SELECT a FROM User a",User.class);
		List <User> result= query.getResultList();
		return result;
	}
	
	@Override
	public User find(int entityID) {
		return em.find(User.class, entityID);
	}




}
