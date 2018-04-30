package tn.esprit.b4.esprit1718b4eventmanagement.services;

import javax.ejb.Local;
import java.util.List;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.User;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.IGenericDAO;

@Local
public interface UserServiceLocal extends IGenericDAO<User> {
	public List<User> DisplayUser() ;
	public User login(String login, String password);
	public List<User> SearchFirstName(String login);
	public List<User> SearchMail(String login);
	public List<User> SearchStatut(String sta);
	public void update1(User u);
	public List<User> SearchLogin(String login);
	public User findByLogin(String l);
	public User userbyfstlstname(String login);
	public User find(int entityID);
}
