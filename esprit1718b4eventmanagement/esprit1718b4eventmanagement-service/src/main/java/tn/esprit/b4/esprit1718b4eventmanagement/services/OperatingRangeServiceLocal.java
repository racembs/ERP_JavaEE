package tn.esprit.b4.esprit1718b4eventmanagement.services;

import javax.ejb.Local;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.OperatingRange;

@Local
public interface OperatingRangeServiceLocal {
	public int addOperatingRange(OperatingRange operatingranges);
	public void deleteOperatingRange(int idOptR);
	public void updateOperatingRange(int idOptR);
	public String findOperatingRange(int idOptR);

}
