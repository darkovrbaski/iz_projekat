package izproject.dto;

import izproject.similiar.model.GPUCaseDescription;
import izproject.similiar.model.MotherboardCaseDescription;
import izproject.similiar.model.ProcessorCaseDescription;
import izproject.similiar.model.RAMCaseDescription;
import izproject.similiar.model.StorageCaseDescription;

public class PCSimilarityDTO {

	private ProcessorCaseDescription processorCaseDescription;

	private MotherboardCaseDescription motherboardCaseDescription;

	private GPUCaseDescription gpuCaseDescription;

	private RAMCaseDescription ramCaseDescription;

	private StorageCaseDescription storageCaseDescription;

	private double similarityValue;

	public PCSimilarityDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PCSimilarityDTO(ProcessorCaseDescription processorCaseDescription,
			MotherboardCaseDescription motherboardCaseDescription, GPUCaseDescription gpuCaseDescription,
			RAMCaseDescription ramCaseDescription, StorageCaseDescription storageCaseDescription,
			double similarityValue) {
		super();
		this.processorCaseDescription = processorCaseDescription;
		this.motherboardCaseDescription = motherboardCaseDescription;
		this.gpuCaseDescription = gpuCaseDescription;
		this.ramCaseDescription = ramCaseDescription;
		this.storageCaseDescription = storageCaseDescription;
		this.similarityValue = similarityValue;
	}

	public ProcessorCaseDescription getProcessorCaseDescription() {
		return processorCaseDescription;
	}

	public void setProcessorCaseDescription(ProcessorCaseDescription processorCaseDescription) {
		this.processorCaseDescription = processorCaseDescription;
	}

	public MotherboardCaseDescription getMotherboardCaseDescription() {
		return motherboardCaseDescription;
	}

	public void setMotherboardCaseDescription(MotherboardCaseDescription motherboardCaseDescription) {
		this.motherboardCaseDescription = motherboardCaseDescription;
	}

	public GPUCaseDescription getGpuCaseDescription() {
		return gpuCaseDescription;
	}

	public void setGpuCaseDescription(GPUCaseDescription gpuCaseDescription) {
		this.gpuCaseDescription = gpuCaseDescription;
	}

	public RAMCaseDescription getRamCaseDescription() {
		return ramCaseDescription;
	}

	public void setRamCaseDescription(RAMCaseDescription ramCaseDescription) {
		this.ramCaseDescription = ramCaseDescription;
	}

	public StorageCaseDescription getStorageCaseDescription() {
		return storageCaseDescription;
	}

	public void setStorageCaseDescription(StorageCaseDescription storageCaseDescription) {
		this.storageCaseDescription = storageCaseDescription;
	}

	public double getSimilarityValue() {
		return similarityValue;
	}

	public void setSimilarityValue(double similarityValue) {
		this.similarityValue = similarityValue;
	}

}
