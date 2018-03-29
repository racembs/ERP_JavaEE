package tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Client;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.GenericDAO;

/**
 * Session Bean implementation class ClientService
 */
@Stateless
@LocalBean
public class ClientService extends GenericDAO<Client> implements ClientServiceRemote, ClientServiceLocal {

    /**
     * Default constructor. 
     */
	@PersistenceContext
	private EntityManager em;
    public ClientService() {
        // TODO Auto-generated constructor stub
    	super(Client.class);
    }
    public int addClient(Client client){
    	em.persist(client);
		return client.getId();
    }
    
    public List<Client> searchClient(String input){
    	TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c WHERE c.company LIKE :input OR c.email LIKE :input"
    			+ " OR c.phoneNumber LIKE :input OR c.code LIKE :input",Client.class);
    	query.setParameter("input", "%" + input + "%");
    	List<Client> results = query.getResultList();
    	return results;
    }
	@Override
	public Client findByCompany(String company) {
		TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c WHERE c.company=:company",Client.class);
    	query.setParameter("company",company);
    	Client results = query.getSingleResult();
    	return results;
	}

}
