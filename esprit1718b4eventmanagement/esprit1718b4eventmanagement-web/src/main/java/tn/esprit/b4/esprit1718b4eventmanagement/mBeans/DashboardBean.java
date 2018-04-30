package tn.esprit.b4.esprit1718b4eventmanagement.mBeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.FacesComponent;
import javax.naming.NamingException;
@FacesComponent("DashboardBean")
@ManagedBean(name="DashboardBean")
@SessionScoped
public class DashboardBean implements Serializable {
	 @PostConstruct
	    public void init() throws NamingException {
	    	
	    }
}
