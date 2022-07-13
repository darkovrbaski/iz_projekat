package izproject.similiar.model;

import es.ucm.fdi.gaia.jcolibri.cbrcore.Attribute;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CaseComponent;

public class GPUCaseDescription implements CaseComponent {

	private String manufacturer;

	private String model;

	private int memory;

	private int clockSpeed;

	public GPUCaseDescription() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GPUCaseDescription(String manufacturer, String model, int memory, int clockSpeed) {
		super();
		this.manufacturer = manufacturer;
		this.model = model;
		this.memory = memory;
		this.clockSpeed = clockSpeed;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getMemory() {
		return memory;
	}

	public void setMemory(int memory) {
		this.memory = memory;
	}
	

	@Override
	public Attribute getIdAttribute() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getClockSpeed() {
		return clockSpeed;
	}

	public void setClockSpeed(int clockSpeed) {
		this.clockSpeed = clockSpeed;
	}

}
