package tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Client;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.User;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.IGenericDAO;

@Remote
public interface ClientServiceRemote extends IGenericDAO<Client> {
	public int addClient(Client client);
	public List<Client> searchClient(String input);
	public Client findByCompany(String company);
}
