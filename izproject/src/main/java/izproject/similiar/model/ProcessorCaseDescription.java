package izproject.similiar.model;

import es.ucm.fdi.gaia.jcolibri.cbrcore.Attribute;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CaseComponent;

public class ProcessorCaseDescription implements CaseComponent {

	private String manufacturer;

	private String model;

	private int numOfCores;

	private float clockSpeed;

	public ProcessorCaseDescription(String manufacturer, String model, int numOfCores, float clockSpeed) {
		super();
		this.manufacturer = manufacturer;
		this.model = model;
		this.numOfCores = numOfCores;
		this.clockSpeed = clockSpeed;
	}

	public ProcessorCaseDescription() {
		super();
		// TODO Auto-generated constructor stub
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

	public float getClockSpeed() {
		return clockSpeed;
	}

	public void setClockSpeed(float clockSpeed) {
		this.clockSpeed = clockSpeed;
	}

	@Override
	public Attribute getIdAttribute() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getNumOfCores() {
		return numOfCores;
	}

	public void setNumOfCores(int numOfCores) {
		this.numOfCores = numOfCores;
	}
}
