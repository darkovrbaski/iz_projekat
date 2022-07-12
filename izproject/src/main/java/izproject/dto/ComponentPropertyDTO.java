package izproject.dto;

public class ComponentPropertyDTO {
	private String componentName;
	private String property;

	public ComponentPropertyDTO(String componentName, String property) {
		super();
		this.componentName = componentName;
		this.property = property;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

}
