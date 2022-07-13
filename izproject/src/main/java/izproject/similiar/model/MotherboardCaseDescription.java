package izproject.similiar.model;

import es.ucm.fdi.gaia.jcolibri.cbrcore.Attribute;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CaseComponent;

public class MotherboardCaseDescription implements CaseComponent {

	private String name;

	private String manufacturer;

	public MotherboardCaseDescription() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MotherboardCaseDescription(String name, String manufacturer) {
		super();
		this.name = name;
		this.manufacturer = manufacturer;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
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
		return "MotherboardCaseDescription [name=" + name + ", manufacturer=" + manufacturer + "]";
	}

}
