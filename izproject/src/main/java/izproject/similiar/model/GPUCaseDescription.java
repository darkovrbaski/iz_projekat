package izproject.similiar.model;

import es.ucm.fdi.gaia.jcolibri.cbrcore.Attribute;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CaseComponent;

public class GPUCaseDescription implements CaseComponent {

	private String name;

	private String manufacturer;

	private int memory;

	private int clockSpeed;

	private float hashRate;

	public GPUCaseDescription() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GPUCaseDescription(String name, String manufacturer, int memory, int clockSpeed, float hashRate) {
		super();
		this.name = name;
		this.manufacturer = manufacturer;
		this.memory = memory;
		this.clockSpeed = clockSpeed;
		this.hashRate = hashRate;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getHashRate() {
		return hashRate;
	}

	public void setHashRate(float hashRate) {
		this.hashRate = hashRate;
	}

}
