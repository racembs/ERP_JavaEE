package tn.esprit.b4.esprit1718b4eventmanagement.UserBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Column;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Equipment;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.User;
import tn.esprit.b4.esprit1718b4eventmanagement.services.EquipementServiceLocal;
import tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceLocal;


@ManagedBean
@ViewScoped
public class UserBean {
	@EJB
	private UserServiceLocal userServiceLocal;
	
	private List<User> userlist = new ArrayList<>();
private   static User userr;
private   static User usermod;

	@PostConstruct
	public void init() {
		userlist = userServiceLocal.findAll();
	
	}

	private User user = new User();
	
	private static Equipment selectedEquipment;



	
	public String UpdateB(User equi){
	
				userr=equi;
				usermod=equi;
		return "UpdateUser.xhtml?faces-redirect=true";
		    
		}

	public  void doSaveOrUpdate() {
		user.setStatut("valable");
		user.setNb("0");
		user.setPost("technician");
		userServiceLocal.save(user);
	
	}
	public String Update() {
		user=userServiceLocal.find(userr.getId());
		user.setPost(usermod.getPost());
		user.setRole(usermod.getRole());
		user.setStatut(usermod.getStatut());
		user.setLogin(usermod.getLogin());
		userServiceLocal.update(user);
		return "ListUser.xhtml?faces-redirect=true";
	
	}

	public UserServiceLocal getUserServiceLocal() {
		return userServiceLocal;
	}

	public void setUserServiceLocal(UserServiceLocal userServiceLocal) {
		this.userServiceLocal = userServiceLocal;
	}

	public List<User> getUserlist() {
		return userlist;
	}

	public void setUserlist(List<User> userlist) {
		this.userlist = userlist;
	}

	
	public  void setUserr(User userr) {
		UserBean.userr = userr;
	}

	public static User getUsermod() {
		return usermod;
	}

	public static void setUsermod(User usermod) {
		UserBean.usermod = usermod;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public static Equipment getSelectedEquipment() {
		return selectedEquipment;
	}

	public static void setSelectedEquipment(Equipment selectedEquipment) {
		UserBean.selectedEquipment = selectedEquipment;
	}

	public UserBean(UserServiceLocal userServiceLocal) {
		super();
		this.userServiceLocal = userServiceLocal;
	}

	public UserBean(List<User> userlist) {
		super();
		this.userlist = userlist;
	}

	public UserBean(User user) {
		super();
		this.user = user;
	}

	public UserBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserBean(UserServiceLocal userServiceLocal, List<User> userlist, User user) {
		super();
		this.userServiceLocal = userServiceLocal;
		this.userlist = userlist;
		this.user = user;
	}

		

		

	public User getUserr() {
		return userr;
	}

	
	
	


}
