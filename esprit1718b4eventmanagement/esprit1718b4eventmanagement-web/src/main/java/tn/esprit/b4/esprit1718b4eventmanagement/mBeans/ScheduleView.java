package tn.esprit.b4.esprit1718b4eventmanagement.mBeans;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
 
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Equipment;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.PreventiveWork;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.User;
import tn.esprit.b4.esprit1718b4eventmanagement.services.EquipementService;
import tn.esprit.b4.esprit1718b4eventmanagement.services.UserService;
import tn.esprit.b4.esprit1718b4eventmanagement.services.WorkPrevService;
 
@ManagedBean
@ViewScoped
public class ScheduleView implements Serializable {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static int idselecteditem;
	private ScheduleModel eventModel;
     private Date firstAlarm;
    private ScheduleModel lazyEventModel;
    private String info;
    private String techno;
    private int freq;
    private String idworkeq;
    private String idworktech;
    private ScheduleEvent event = new DefaultScheduleEvent();
    private List<PreventiveWork> pworks;
    private List<Equipment> equis;
    private List<User> users;
	@EJB
	  WorkPrevService wpservice;
	@EJB
	  UserService uservice;
	@EJB
	  EquipementService eqservice;
	
	public List<PreventiveWork> getPworks()
	{return wpservice.DisplayPWorks();
	}
	public List<Equipment> getEquis()
	{return eqservice.findAll();
	}
	public List<User> getUsers()
	{return uservice.findAll();
	}
 
    @PostConstruct
    public void init() {
        eventModel = new DefaultScheduleModel();
       
        for(PreventiveWork pw:wpservice.DisplayPWorks())
        {
        	 eventModel.addEvent(new DefaultScheduleEvent(pw.getObjet(), pw.getStartDate(), pw.getEndDate()));
        }
       
              
        lazyEventModel = new LazyScheduleModel() {
             
            @Override
            public void loadEvents(Date start, Date end) {
                Date random = getRandomDate(start);
                addEvent(new DefaultScheduleEvent("Lazy Event 1", random, random));
                 
                random = getRandomDate(start);
                addEvent(new DefaultScheduleEvent("Lazy Event 2", random, random));
            }   
        };
    }
     
    public Date getRandomDate(Date base) {
        Calendar date = Calendar.getInstance();
        date.setTime(base);
        date.add(Calendar.DATE, ((int) (Math.random()*30)) + 1);    //set random day of month
         
        return date.getTime();
    }
     
    public Date getInitialDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), Calendar.FEBRUARY, calendar.get(Calendar.DATE), 0, 0, 0);
         
        return calendar.getTime();
    }
     
    public ScheduleModel getEventModel() {
        return eventModel;
    }
     
    public ScheduleModel getLazyEventModel() {
        return lazyEventModel;
    }
 
    private Calendar today() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
 
        return calendar;
    }
     
    private Date previousDay8Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
        t.set(Calendar.HOUR, 8);
         
        return t.getTime();
    }
     
    private Date previousDay11Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
        t.set(Calendar.HOUR, 11);
         
        return t.getTime();
    }
     
    private Date today1Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 1);
         
        return t.getTime();
    }
     
    private Date theDayAfter3Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 2);     
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 3);
         
        return t.getTime();
    }
 
    private Date today6Pm() {
        Calendar t = (Calendar) today().clone(); 
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 6);
         
        return t.getTime();
    }
     
    private Date nextDay9Am() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.AM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
        t.set(Calendar.HOUR, 9);
         
        return t.getTime();
    }
     
    private Date nextDay11Am() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.AM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
        t.set(Calendar.HOUR, 11);
         
        return t.getTime();
    }
     
    private Date fourDaysLater3pm() {
        Calendar t = (Calendar) today().clone(); 
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 4);
        t.set(Calendar.HOUR, 3);
         
        return t.getTime();
    }
     
    public ScheduleEvent getEvent() {
        return event;
    }
 
    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }
     
    public void addEvent(ActionEvent actionEvent) {
        if(event.getId() == null)
        { 	FacesContext context = FacesContext.getCurrentInstance();
        
        context.addMessage(null, new FacesMessage("Successful",  "preventive work add " ) );
        	eventModel.addEvent(event);
        PreventiveWork pp=new PreventiveWork();
        pp.setCreatDate(new Date());
        pp.setDescription(info);
        pp.setEndDate(event.getEndDate());
        pp.setStartDate(event.getStartDate());
        pp.setTrigger(firstAlarm);
        pp.setFrequency(String.valueOf(freq));
        pp.setObjet(event.getTitle());
        pp.setTechnology(techno);
        wpservice.addWP(pp);}
        else
        {
        	FacesContext context = FacesContext.getCurrentInstance();
            
            context.addMessage(null, new FacesMessage("Successful",  "preventive work updated " ) );
     

        	System.out.println("updaaaaate"+event.getTitle()); 
    PreventiveWork wp=  wpservice.findID(idselecteditem);
  	System.out.println("updaaaaate"+wp.getId()); 
  		wp.setObjet(event.getTitle());
      wp.setDescription(info);
      wp.setEndDate(event.getEndDate());
      wp.setStartDate(event.getStartDate());
      wp.setTrigger(firstAlarm);
      wp.setFrequency(String.valueOf(freq));
      wp.setObjet(event.getTitle());
      wp.setTechnology(techno);
      wpservice.update(wp);
      eventModel.updateEvent(event);
       }
        event = new DefaultScheduleEvent();
    }
    public void delete2(ActionEvent actionEvent) {
    	  PreventiveWork wp=  wpservice.findObject(event.getTitle());
    	  wpservice.remove(wp.getId());
       eventModel.deleteEvent(event);
     event = new DefaultScheduleEvent();
    }
     
    public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleEvent) selectEvent.getObject();
    }
     
    public void onDateSelect(SelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
    }
     
    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
         
        addMessage(message);
    }
     
    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
         
        addMessage(message);
    }
     
    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

	public WorkPrevService getWpservice() {
		return wpservice;
	}

	public void setWpservice(WorkPrevService wpservice) {
		this.wpservice = wpservice;
	}

	public void setPworks(List<PreventiveWork> pworks) {
		this.pworks = pworks;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Date getFirstAlarm() {
		return firstAlarm;
	}

	public void setFirstAlarm(Date firstAlarm) {
		this.firstAlarm = firstAlarm;
	}

	public int getFreq() {
		return freq;
	}

	public void setFreq(int freq) {
		this.freq = freq;
	}

	public String getTechno() {
		return techno;
	}

	public void setTechno(String techno) {
		this.techno = techno;
	}

	public String getIdworkeq() {
		return idworkeq;
	}

	public void setIdworkeq(String string) {
		this.idworkeq = string;
	}

	public String getIdworktech() {
		return idworktech;
	}

	public void setIdworktech(String string) {
		this.idworktech = string;
	}

	public void m(SelectEvent selectEvent) {
		   event = (ScheduleEvent) selectEvent.getObject();
		   PreventiveWork prev=wpservice.findObject(event.getTitle());
		   idselecteditem=prev.getId();
		 
		   
		this.setFirstAlarm(prev.getTriggerD());
		  this.setInfo(prev.getDescription());  
		   this.setTechno(prev.getTechnology());
		 this.setFreq(Integer.parseInt(prev.getFrequency()));
		 this.setIdworkeq(prev.getEquipement().getSerialNum()+"/"+prev.getEquipement().getLieu()+"/"+prev.getEquipement().getArboresence().getName());
		     
		  this.setIdworktech(uservice.find(prev.getTechnicianId()).getFirstname()+"-"+uservice.find(prev.getTechnicianId()).getLastname());
		  
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((equis == null) ? 0 : equis.hashCode());
		result = prime * result + ((firstAlarm == null) ? 0 : firstAlarm.hashCode());
		result = prime * result + freq;
		result = prime * result + ((idworkeq == null) ? 0 : idworkeq.hashCode());
		result = prime * result + ((idworktech== null) ? 0 : idworktech.hashCode());
		result = prime * result + ((info == null) ? 0 : info.hashCode());
		result = prime * result + ((pworks == null) ? 0 : pworks.hashCode());
		result = prime * result + ((techno == null) ? 0 : techno.hashCode());
		result = prime * result + ((users == null) ? 0 : users.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScheduleView other = (ScheduleView) obj;
		if (equis == null) {
			if (other.equis != null)
				return false;
		} else if (!equis.equals(other.equis))
			return false;
		if (firstAlarm == null) {
			if (other.firstAlarm != null)
				return false;
		} else if (!firstAlarm.equals(other.firstAlarm))
			return false;
		if (freq != other.freq)
			return false;
		if (idworkeq == null) {
			if (other.idworkeq != null)
				return false;
		} else if (!idworkeq.equals(other.idworkeq))
			return false;
		if (idworktech != other.idworktech)
			return false;
		if (info == null) {
			if (other.info != null)
				return false;
		} else if (!info.equals(other.info))
			return false;
		if (pworks == null) {
			if (other.pworks != null)
				return false;
		} else if (!pworks.equals(other.pworks))
			return false;
		if (techno == null) {
			if (other.techno != null)
				return false;
		} else if (!techno.equals(other.techno))
			return false;
		if (users == null) {
			if (other.users != null)
				return false;
		} else if (!users.equals(other.users))
			return false;
		return true;
	}
	   public void delete(SelectEvent selectEvent) {
		   event = (ScheduleEvent) selectEvent.getObject();
		   PreventiveWork prev=wpservice.findObject(event.getTitle());
		   wpservice.remove(prev.getId());
			System.out.println("deleeeeeeeeeeet"); 
	    }
	   public void delete1() {
	FacesContext context = FacesContext.getCurrentInstance();
            
            context.addMessage(null, new FacesMessage("Successful",  "preventive work deleted " ) );
		System.out.println("deleeeeeeeeeeet");
		System.out.println(event.getTitle()); 
			PreventiveWork prev=wpservice.findObject(event.getTitle());
		   wpservice.remove(prev.getId());
			System.out.println("deleeeeeeeeeeet"); 
		
	    }
}