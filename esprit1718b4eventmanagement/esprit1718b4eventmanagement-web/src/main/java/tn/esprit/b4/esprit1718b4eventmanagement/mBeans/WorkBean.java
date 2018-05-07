package tn.esprit.b4.esprit1718b4eventmanagement.mBeans;
import java.util.List;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.PreventiveWork;
import tn.esprit.b4.esprit1718b4eventmanagement.services.WorkPrevService;





@ManagedBean
@SessionScoped
public class WorkBean {
	private List<PreventiveWork> pworks;
	@EJB
	  WorkPrevService wpservice;
	public List<PreventiveWork> getPworks()
	{return wpservice.DisplayPWorks();
	
		
	}
}
