package izproject.similiar.model;

import es.ucm.fdi.gaia.jcolibri.cbrcore.Attribute;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CaseComponent;

public class StorageCaseDescription implements CaseComponent {

	private String type;

	private int capacity;

	public StorageCaseDescription() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StorageCaseDescription(String type, int capacity) {
		super();
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

}
