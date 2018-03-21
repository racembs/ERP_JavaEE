package tn.esprit.b4.esprit1718b4eventmanagement.services;
import java.util.List;


import javax.ejb.Local;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.SpareParts;


@Local
public interface NeedSparePartsServiceLocal {
	public int addSpareParts(SpareParts SpareParts);
	public void deleteSpareParts(int idSpare);
	public void updateSpareParts(SpareParts SpareParts);
	public SpareParts findSparePartsById(int idSpare);
	public SpareParts findSparePartsByRef(String ref);
	public List<SpareParts> displayAll();
}
