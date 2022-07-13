package izproject.similiar.model;

import es.ucm.fdi.gaia.jcolibri.cbrcore.Attribute;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CaseComponent;

public class ProcessorCaseDescription implements CaseComponent {

	private String name;

	private String manufacturer;

	private int numOfCores;

	private float clockSpeed;

	public ProcessorCaseDescription(String name, String manufacturer, int numOfCores, float clockSpeed) {
		super();
		this.name = name;
		this.manufacturer = manufacturer;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ProcessorCaseDescription [name=" + name + ", manufacturer=" + manufacturer + ", numOfCores="
				+ numOfCores + ", clockSpeed=" + clockSpeed + "]";
	}

}
