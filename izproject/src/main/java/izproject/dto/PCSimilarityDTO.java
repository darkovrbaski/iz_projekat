package izproject.dto;

public class PCSimilarityDTO {

	private String processor;

	private String motherboard;

	private String gpu;

	private String ram;

	private String storage;

	private double similarityValue;

	public PCSimilarityDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PCSimilarityDTO(String processor, String motherboard, String gpu, String ram, String storage,
			double similarityValue) {
		super();
		this.processor = processor;
		this.motherboard = motherboard;
		this.gpu = gpu;
		this.ram = ram;
		this.storage = storage;
		this.similarityValue = similarityValue;
	}

	public String getProcessor() {
		return processor;
	}

	public void setProcessor(String processor) {
		this.processor = processor;
	}

	public String getMotherboard() {
		return motherboard;
	}

	public void setMotherboard(String motherboard) {
		this.motherboard = motherboard;
	}

	public String getGpu() {
		return gpu;
	}

	public void setGpu(String gpu) {
		this.gpu = gpu;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public double getSimilarityValue() {
		return similarityValue;
	}

	public void setSimilarityValue(double similarityValue) {
		this.similarityValue = similarityValue;
	}

}
