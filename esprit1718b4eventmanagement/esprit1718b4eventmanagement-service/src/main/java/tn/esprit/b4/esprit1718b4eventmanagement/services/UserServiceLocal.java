package tn.esprit.b4.esprit1718b4eventmanagement.services;

import javax.ejb.Local;
import java.util.List;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.User;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.IGenericDAO;

@Local
public interface UserServiceLocal extends IGenericDAO<User> {

	User login(String login, String password);
	public User add(User u);
	public User update(User u);
	public Boolean remove(Integer id);
	public List<User> findAll();
	public User findById(Integer id);
	public User findByLogin(String l);

}
