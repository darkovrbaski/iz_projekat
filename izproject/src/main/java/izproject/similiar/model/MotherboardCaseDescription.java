package izproject.similiar.model;

import es.ucm.fdi.gaia.jcolibri.cbrcore.Attribute;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CaseComponent;

public class MotherboardCaseDescription implements CaseComponent {

	private String manufacturer;

	private String model;

	public MotherboardCaseDescription() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MotherboardCaseDescription(String manufacturer, String model) {
		super();
		this.manufacturer = manufacturer;
		this.model = model;
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

	@Override
	public Attribute getIdAttribute() {
		// TODO Auto-generated method stub
		return null;
	}

}
