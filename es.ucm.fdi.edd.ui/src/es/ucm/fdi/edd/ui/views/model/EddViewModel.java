package es.ucm.fdi.edd.ui.views.model;

import es.ucm.fdi.edd.core.erlang.model.EddModel;

public class EddViewModel extends ModelObject {

	private String name = "Prueba";
	private String[] programmingSkills;
	private int amount = 0;
	
	private EddModel eddModel;
	
	/**
	 * Constructor
	 */
	public EddViewModel() {
		// 
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		firePropertyChange("name", this.name, this.name = name);
	}

	public String[] getProgrammingSkills() {
		return programmingSkills;
	}

	public void setProgrammingSkills(String[] programmingSkills) {
		firePropertyChange("programmingSkills", this.programmingSkills,	this.programmingSkills = programmingSkills);
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int newAmount) {
		int oldAmount = this.amount;
		this.amount = newAmount;
		getChangeSupport().firePropertyChange("amount", oldAmount, newAmount);
	}
	
	public EddModel getEddModel() {
		return eddModel;
	}
	
	public void setEddModel(EddModel eddModel) {
	    getChangeSupport().firePropertyChange("eddModel", this.eddModel, this.eddModel = eddModel);
	}
}