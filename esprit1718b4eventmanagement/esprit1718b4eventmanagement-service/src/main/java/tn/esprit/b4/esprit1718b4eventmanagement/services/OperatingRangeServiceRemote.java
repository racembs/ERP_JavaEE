package tn.esprit.b4.esprit1718b4eventmanagement.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.OperatingRange;

@Remote
public interface OperatingRangeServiceRemote {
	public int addOperatingRange(OperatingRange operatingranges);
	public void deleteOperatingRange(int idOptR);
	void updateOperatingRange(OperatingRange OptRange);
	public String findOperatingRange(int idOptR);
	public List<OperatingRange> DisplayOperatingRange();
	public Long CountOperatingRange();
	public List<OperatingRange> find(String code);
}
