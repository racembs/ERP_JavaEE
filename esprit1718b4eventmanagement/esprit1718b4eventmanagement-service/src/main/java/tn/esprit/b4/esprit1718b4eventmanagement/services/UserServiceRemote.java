package tn.esprit.b4.esprit1718b4eventmanagement.services;


import java.util.List;
import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.User;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.IGenericDAO;

@Remote
public interface UserServiceRemote extends IGenericDAO<User> {


	public void update1(User u);



	public User findByLogin(String l);

}
