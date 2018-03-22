package tn.esprit.b4.esprit1718b4eventmanagement.services;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.SpareParts;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Tool;
import javax.ejb.Local;

@Local
public interface NeedToolServiceLocal {
	public void addTool(Tool T);
	public void updateTool(Tool T);
	public void deleteTool(int idT);
	public SpareParts findToolById(int idTool);
	public SpareParts findToolByRef(String ref);
}
