package izproject.similiar.model;

import es.ucm.fdi.gaia.jcolibri.cbrcore.Attribute;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CaseComponent;

public class PCCaseDescription implements CaseComponent {

	private ProcessorCaseDescription processorCaseDescription;

	private MotherboardCaseDescription motherboardCaseDescription;

	private GPUCaseDescription gpuCaseDescription;

	private RAMCaseDescription ramCaseDescription;

	private StorageCaseDescription storageCaseDescription;

	public PCCaseDescription() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PCCaseDescription(ProcessorCaseDescription processorCaseDescription,
			MotherboardCaseDescription motherboardCaseDescription, GPUCaseDescription gpuCaseDescription,
			RAMCaseDescription ramCaseDescription, StorageCaseDescription storageCaseDescription) {
		super();
		this.processorCaseDescription = processorCaseDescription;
		this.motherboardCaseDescription = motherboardCaseDescription;
		this.gpuCaseDescription = gpuCaseDescription;
		this.ramCaseDescription = ramCaseDescription;
		this.storageCaseDescription = storageCaseDescription;
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

	@Override
	public Attribute getIdAttribute() {
		// TODO Auto-generated method stub
		return null;
	}

}
