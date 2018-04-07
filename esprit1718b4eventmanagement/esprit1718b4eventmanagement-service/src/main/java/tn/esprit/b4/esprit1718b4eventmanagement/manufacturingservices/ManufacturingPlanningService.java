package tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.ManufacturingPlanning;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.NeededItem;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.OperatingRange;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Operation;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.GenericDAO;

/**
 * Session Bean implementation class ManufacturingPlanningService
 */
@Stateless
@LocalBean
public class ManufacturingPlanningService extends GenericDAO<ManufacturingPlanning> implements ManufacturingPlanningServiceRemote, ManufacturingPlanningServiceLocal {

	@EJB
	NeededItemServiceLocal needItem;
	
    /**
     * Default constructor. 
     */
    public ManufacturingPlanningService() {
        // TODO Auto-generated constructor stub
    	super(ManufacturingPlanning.class);
    }

	@Override
	public long manufacturingDuration(Article article,int quantity) {
		int duration = 0;
		//liste of operating range
		List<OperatingRange> listOp = article.getOperatingranges();
		for (OperatingRange operatingRange : listOp) {
			List<Operation> List = operatingRange.getOperations();
			for (Operation operation : List) {
				duration = duration + (quantity/operation.getUnitproductiontime());
			}
		}
		duration = Math.round(duration*60);
		return duration;
	}

	@Override
	public Date endingManufacturingDate(Date startingDate, long duration) {
		float hourNbr=duration/60;
		float dayNbr=hourNbr/9;
		int length = (int) Math.round(dayNbr+0.5);
		Calendar c = Calendar.getInstance();
	    c.setTime(startingDate);
	    long startMillis = c.getTimeInMillis();
	    long endMillis=0;
	    //if it's a work doesn't exceed 1 day 
	    if((length-1)==1){
	    	long testEndMillis = startMillis + duration*60*1000;
	    	//check if the work is more than 17h
	    	//86400000 =1 day and 61200000=17h and 54000000=15h
	    	if((testEndMillis%(86400000))>61200000){
	    		endMillis=testEndMillis+54000000;
	    	}
	    	else{
	    		endMillis=testEndMillis;
	    	}
	    }
	    else{
	    	long newDuration = duration-(length-1)*9*60;
	    	long newStartMillis = startMillis + (length-1)*24*60*60*1000;
	    	
	    	long testEndMillis = newStartMillis + newDuration*60*1000;
	    	//check if the work is more than 17h
	    	//86400000 =1 day and 61200000=17h and 54000000=15h
	    	if((testEndMillis%(86400000))>61200000){
	    		endMillis=testEndMillis+54000000;
	    	}
	    	else{
	    		endMillis=testEndMillis;
	    	}
	    }
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTimeInMillis(endMillis);
	    
	    Date endingDate =calendar.getTime();
		return endingDate;
	}
	
	
    

}
