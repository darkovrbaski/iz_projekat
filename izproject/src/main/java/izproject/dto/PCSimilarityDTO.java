package izproject.dto;

public class PCSimilarityDTO {

	private String cpuName;

	private String motherboardName;

	private String gpuName;

	private String ramName;

	private String storageName;

	private double similarityValue;

	public PCSimilarityDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PCSimilarityDTO(String cpuName, String motherboardName, String gpuName, String ramName, String storageName,
			double similarityValue) {
		super();
		this.cpuName = cpuName;
		this.motherboardName = motherboardName;
		this.gpuName = gpuName;
		this.ramName = ramName;
		this.storageName = storageName;
		this.similarityValue = similarityValue;
	}

	public String getCpuName() {
		return cpuName;
	}

	public void setCpuName(String cpuName) {
		this.cpuName = cpuName;
	}

	public String getMotherboardName() {
		return motherboardName;
	}

	public void setMotherboardName(String motherboardName) {
		this.motherboardName = motherboardName;
	}

	public String getGpuName() {
		return gpuName;
	}

	public void setGpuName(String gpuName) {
		this.gpuName = gpuName;
	}

	public String getRamName() {
		return ramName;
	}

	public void setRamName(String ramName) {
		this.ramName = ramName;
	}

	public String getStorageName() {
		return storageName;
	}

	public void setStorageName(String storageName) {
		this.storageName = storageName;
	}

	public double getSimilarityValue() {
		return similarityValue;
	}

	public void setSimilarityValue(double similarityValue) {
		this.similarityValue = similarityValue;
	}

}
