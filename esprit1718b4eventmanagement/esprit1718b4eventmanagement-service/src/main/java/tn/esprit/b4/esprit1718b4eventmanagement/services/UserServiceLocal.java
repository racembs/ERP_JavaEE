package tn.esprit.b4.esprit1718b4eventmanagement.services;

import javax.ejb.Local;
import java.util.List;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.User;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.IGenericDAO;

@Local
public interface UserServiceLocal extends IGenericDAO<User> {

	User login(String login, String password);

	public void update1(User u);
	public List<User> SearchLogin(String login);
	public User findByLogin(String l);

}
