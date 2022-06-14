package izproject.dto;

import java.util.List;

public class MalfunctionSpecsDTO {

	private String processor;
	private String memoryType;
	private String memorySize;
	private String storageType;
	private String graphicCard;
	private String motherboard;
	private String mouseManufacturer;
	private String keyboardType;
	private String keyboardCaseType;
	private String mouseType;
	private String operatingSystem;
	private String antivirusSoftware;
	private String monitor;
	private String cmosUsage;
	private List<String> symptoms;

	public String getCmosUsage() {
		return cmosUsage;
	}

	public void setCmosUsage(String cmosUsage) {
		this.cmosUsage = cmosUsage;
	}

	public String getMonitor() {
		return monitor;
	}

	public void setMonitor(String monitor) {
		this.monitor = monitor;
	}

	public String getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public String getAntivirusSoftware() {
		return antivirusSoftware;
	}

	public void setAntivirusSoftware(String antivirusSoftware) {
		this.antivirusSoftware = antivirusSoftware;
	}

	public List<String> getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(List<String> symptoms) {
		this.symptoms = symptoms;
	}

	public String getProcessor() {
		return processor;
	}

	public void setProcessor(String processor) {
		this.processor = processor;
	}

	public String getMemoryType() {
		return memoryType;
	}

	public void setMemoryType(String memoryType) {
		this.memoryType = memoryType;
	}

	public String getMemorySize() {
		return memorySize;
	}

	public void setMemorySize(String memorySize) {
		this.memorySize = memorySize;
	}

	public String getGraphicCard() {
		return graphicCard;
	}

	public void setGraphicCard(String graphicCard) {
		this.graphicCard = graphicCard;
	}

	public String getStorageType() {
		return storageType;
	}

	public void setStorageType(String storageType) {
		this.storageType = storageType;
	}

	public String getMotherboard() {
		return motherboard;
	}

	public void setMotherboard(String motherboard) {
		this.motherboard = motherboard;
	}

	public String getMouseManufacturer() {
		return mouseManufacturer;
	}

	public void setMouseManufacturer(String mouseManufacturer) {
		this.mouseManufacturer = mouseManufacturer;
	}

	public String getKeyboardType() {
		return keyboardType;
	}

	public void setKeyboardType(String keyboardType) {
		this.keyboardType = keyboardType;
	}

	public String getKeyboardCaseType() {
		return keyboardCaseType;
	}

	public void setKeyboardCaseType(String keyboardCaseType) {
		this.keyboardCaseType = keyboardCaseType;
	}

	public String getMouseType() {
		return mouseType;
	}

	public void setMouseType(String mouseType) {
		this.mouseType = mouseType;
	}

}
