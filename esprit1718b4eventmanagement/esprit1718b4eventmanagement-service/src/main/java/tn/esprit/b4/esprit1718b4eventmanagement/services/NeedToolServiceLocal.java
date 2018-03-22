package tn.esprit.b4.esprit1718b4eventmanagement.services;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Tool;
import java.util.List;
import javax.ejb.Local;

@Local
public interface NeedToolServiceLocal {
	public void addTool(Tool T);
	public void updateTool(Tool T);
	public void deleteTool(int idT);
	public Tool findToolById(int idTool);
	public Tool findToolByRef(String ref);
	public List<Tool> displayAvailability(boolean av);
	public List<Tool> displayAll();
}
