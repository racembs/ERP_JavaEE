package tn.esprit.b4.esprit1718b4eventmanagement.mBeans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.User;
import tn.esprit.b4.esprit1718b4eventmanagement.services.UserService;
import tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceLocal;

@ManagedBean(name="identity")
@SessionScoped
public class Identity {
	private boolean isLogged = false;
	

	
	public static  User user ;
	@EJB
	private UserServiceLocal userSer;
	private static String login ;
	private static String password ;
	
	
	

	public void setUserServiceLocal(UserService userServiceLocal) {
		this.userSer = userServiceLocal;
	}

	public String logout() {
		isLogged = false;
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/login?faces-redirect=true";
	}

	public String doLogin() {
		String navigateTo = "";


		user = userSer.login(login,password);
		
		if (user.getRole().equals("GMAO")) {
			
			
			navigateTo = "/Equipement/Equipement?faces-redirect=true";

		}
		else if (user.getRole().equals("GPAO")) {
			
			
			navigateTo = "/articleManagement?faces-redirect=true";
		}
	else if (user.getRole().equals("Client")) {
			
			
			navigateTo = "/Operation/client?faces-redirect=true";
		}
	else if (user.getRole().equals("Operator")) {
		
		
		navigateTo = "/Operation/disc/index?faces-redirect=true";
	}
			else {
			
			return "/login?faces-redirect=true";

		}
		return navigateTo;

	}




	public void setLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}

	public Boolean getIsLogged() {
		return isLogged;
	}

	public void setIsLogged(Boolean isLogged) {
		this.isLogged = isLogged;
	}



	public  String getLogin() {
		return login;
	}

	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public UserServiceLocal getUserSer() {
		return userSer;
	}

	public void setUserSer(UserServiceLocal userSer) {
		this.userSer = userSer;
	}

	public void setUserSer(UserService userSer) {
		this.userSer = userSer;
	}

	public Identity(boolean isLogged, User user, UserService userSer) {
		super();
		this.isLogged = isLogged;
		this.user = user;
		this.userSer = userSer;
	}

	public Identity() {
		super();
		// TODO Auto-generated constructor stub
	}


	

}
