package tn.esprit.b4.esprit1718b4eventmanagement.services;


import java.util.List;
import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.User;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.IGenericDAO;

@Remote
public interface UserServiceRemote extends IGenericDAO<User> {
	public User add(User u);

	public User update(User u);

	public Boolean remove(Integer id);

	public List<User> findAll();

	public User findById(Integer id);

	public User findByLogin(String l);

}
