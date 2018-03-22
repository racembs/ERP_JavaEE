package tn.esprit.b4.esprit1718b4eventmanagement.services;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.OperatingRange;

@Remote
public interface OperatingRangeServiceRemote {
	public int addOperatingRange(OperatingRange operatingranges);
	public void deleteOperatingRange(int idOptR);
	public void updateOperatingRange(int idOptR);
	public String findOperatingRange(int idOptR);

}
