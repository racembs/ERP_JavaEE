package tn.esprit.b4.esprit1718b4eventmanagement.services;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Tool;
import javax.ejb.Remote;


@Remote
public interface NeedToolServiceRemote {
	public void addTool(Tool  T);
	public void updateTool(Tool T);
	public void deleteTool(int idT);
	public Tool displayTools();
}
