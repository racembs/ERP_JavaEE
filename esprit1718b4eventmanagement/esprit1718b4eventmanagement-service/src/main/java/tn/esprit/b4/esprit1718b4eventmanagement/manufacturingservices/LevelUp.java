package tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices;

import java.io.Serializable;
import java.util.Comparator;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.NeededItem;


public class LevelUp implements Serializable, Comparator<NeededItem> {

	@Override
	public int compare(NeededItem o1, NeededItem o2) {
		if(o1.getLevel()!=o2.getLevel())
			return o1.getLevel()-o2.getLevel();
		else
			return o1.compareTo(o2);
	}

}
