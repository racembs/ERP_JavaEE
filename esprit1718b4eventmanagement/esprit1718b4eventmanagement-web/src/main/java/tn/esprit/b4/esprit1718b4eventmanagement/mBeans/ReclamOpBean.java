package tn.esprit.b4.esprit1718b4eventmanagement.mBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Equipment;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Reclamation;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.User;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleService;
import tn.esprit.b4.esprit1718b4eventmanagement.services.EquipementService;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ReclamationService;
import tn.esprit.b4.esprit1718b4eventmanagement.services.UserService;

@FacesComponent("ReclamOpBean")
@ManagedBean(name="ReclamOpBean")
@SessionScoped
public class ReclamOpBean implements Serializable {
	private String email;
	private String description;
	private String subject;
	private String userFirstName;
	private String code;
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	private Article article;
	private User user;
	
	private static int eqid;
	private static int uid;
	
	
	@EJB
	UserService UserServices;
	
	@EJB
	ArticleService ArticleServices;
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}


	private static final long serialVersionUID = 1L;
	
	@EJB
	ReclamationService ReclamationServices;
	
	
    @PostConstruct
    public void init() throws NamingException {
    	
    }
    
    public void AddReclam()
	{ 
	  Reclamation Reclam = new Reclamation();
	  Calendar c = Calendar.getInstance();
	  Reclam.setCode(1);
 Reclam.setDatecreation(c.getTime());
  Reclam.setSubject(subject);
	  Reclam.setDescription(description);
//	  
//	  Equipment eq = new Equipment();
//	  eq=EquipementService.findEquipementBySerie(serialNum);
  
	 
	  List<Article> listART=ArticleServices.findArticleByCode(code);
	  for (int i = 0; i < listART.size(); i++) {
		  eqid=listART.get(i).getId();
	}
	  User us = new User();
//	  us=UserServices.findByLogin(userFirstName);
//	  

	  uid=Identity.user.getId();
//	  

	ReclamationServices.addReclamation(eqid,uid, Reclam);
	FacesContext context1 = FacesContext.getCurrentInstance();
    context1.addMessage(null, new FacesMessage("Your complaint has been sent" ) );

	}
    
    public List<String> findUser(String query){
        List<User> list=UserServices.findAll();
        List<String> sList =new ArrayList<>();
        for(int i=0;i<list.size();i++){
        	if(list.get(i).getLogin().toUpperCase().contains(query)||list.get(i).getLogin().toLowerCase().contains(query)){
        		sList.add(list.get(i).getLogin());
        	}
        
        }
        return sList;
        }
        
        public List<String> findArticle(String query){
            List<Article> list=ArticleServices.DisplayArticle();
            List<String> sList =new ArrayList<>();
            for(int i=0;i<list.size();i++){
            	if(list.get(i).getArticleCode().toUpperCase().contains(query)||list.get(i).getArticleCode().toLowerCase().contains(query)){
            		sList.add(list.get(i).getArticleCode());
            	}
            
            }
            return sList;
            }



		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public static int getEqid() {
			return eqid;
		}

		public static void setEqid(int eqid) {
			ReclamOpBean.eqid = eqid;
		}

		public static int getUid() {
			return uid;
		}

		public static void setUid(int uid) {
			ReclamOpBean.uid = uid;
		}
	
}
