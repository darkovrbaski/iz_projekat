package izproject.similiar.model;

import es.ucm.fdi.gaia.jcolibri.cbrcore.Attribute;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CaseComponent;

public class StorageCaseDescription implements CaseComponent {

	private String name;

	private String type;

	private int capacity;

	public StorageCaseDescription() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StorageCaseDescription(String name, String type, int capacity) {
		super();
		this.name = name;
		this.type = type;
		this.capacity = capacity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public Attribute getIdAttribute() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "StorageCaseDescription [name=" + name + ", type=" + type + ", capacity=" + capacity + "]";
	}

}
