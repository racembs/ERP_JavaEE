package tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices;

import java.io.Serializable;
import java.util.Comparator;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.NeededItem;


public class LevelDown implements Serializable, Comparator<NeededItem> {

	@Override
	public int compare(NeededItem o1, NeededItem o2) {
		if(o2.getLevel()!=o1.getLevel())
			return o2.getLevel()-o1.getLevel();
		else if(o2.compareTo(o1)!=0)
			return o2.compareTo(o1);
		else
			return (int) ((o2.getRand()- o1.getRand())*100);
	}

}
