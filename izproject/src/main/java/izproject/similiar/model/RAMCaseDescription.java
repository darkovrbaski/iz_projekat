package izproject.similiar.model;

import es.ucm.fdi.gaia.jcolibri.cbrcore.Attribute;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CaseComponent;

public class RAMCaseDescription implements CaseComponent {

	private String name;

	private String type;

	private int frequency;

	private int capacity;

	public RAMCaseDescription() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RAMCaseDescription(String name, String type, int frequency, int capacity) {
		super();
		this.name = name;
		this.type = type;
		this.frequency = frequency;
		this.capacity = capacity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	@Override
	public Attribute getIdAttribute() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "RAMCaseDescription [name=" + name + ", type=" + type + ", frequency=" + frequency + ", capacity="
				+ capacity + "]";
	}

}
