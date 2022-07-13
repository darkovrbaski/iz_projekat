package izproject.similiar;

import java.net.URL;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import es.ucm.fdi.gaia.jcolibri.cbrcore.CBRCase;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CaseBaseFilter;
import es.ucm.fdi.gaia.jcolibri.cbrcore.Connector;
import es.ucm.fdi.gaia.jcolibri.exception.InitializingException;
import izproject.service.ComponentService;
import izproject.similiar.model.GPUCaseDescription;
import izproject.similiar.model.MotherboardCaseDescription;
import izproject.similiar.model.PCCaseDescription;
import izproject.similiar.model.ProcessorCaseDescription;
import izproject.similiar.model.RAMCaseDescription;
import izproject.similiar.model.StorageCaseDescription;

public class OntologyConnector implements Connector {

	@Autowired
	ComponentService componentService;

	@Override
	public Collection<CBRCase> retrieveSomeCases(CaseBaseFilter filter) {
		LinkedList<CBRCase> cases = new LinkedList<CBRCase>();

		try {
			List<String> pcs = componentService.getComponents("PersonalComputer");

			String processor = "";
			String motherboard = "";
			String gpu = "";
			String ram = "";
			String storage = "";

			String processorManufacturer = "";
			int numOfCores;
			float processorClockSpeed;

			String motherboardManufacturer = "";

			String gpuManufacturer = "";
			int gpuMemory;
			int gpuClockSpeed = 0;
			float gpuHashRate;

			String ramType = "";
			int ramFrequency;
			int ramCapacity;

			String storageType = "";
			int storageCapacity;

			for (String pc : pcs) {

				CBRCase cbrCase = new CBRCase();

				processor = componentService.getComponentObjectProperty(pc, "hasProcessor");
				processorManufacturer = processor.split("_")[0];
				numOfCores = Integer.parseInt(componentService.getComponentDataProperty(processor, "processorCores"));
				processorClockSpeed = Float
						.parseFloat(componentService.getComponentDataProperty(processor, "processorClock"));
				ProcessorCaseDescription processorCaseDescription = new ProcessorCaseDescription(processor,
						processorManufacturer, numOfCores, processorClockSpeed);

				motherboard = componentService.getComponentObjectProperty(pc, "hasMotherboard");
				motherboardManufacturer = motherboard.split("_")[0];
				MotherboardCaseDescription motherboardCaseDescription = new MotherboardCaseDescription(motherboard,
						motherboardManufacturer);

				gpu = componentService.getComponentObjectProperty(pc, "hasGPU");
				gpuManufacturer = gpu.split("_")[0];
				gpuMemory = Integer.parseInt(componentService.getComponentDataProperty(gpu, "graphicCardMemory"));
				gpuHashRate = Float.parseFloat(componentService.getComponentDataProperty(gpu, "graphicCardHashRate"));
				GPUCaseDescription gpuCaseDescription = new GPUCaseDescription(gpu, gpuManufacturer, gpuMemory,
						gpuClockSpeed,gpuHashRate);

				ram = componentService.getComponentObjectProperty(pc, "hasRAM");
				ramType = componentService.getComponentType(ram);
				ramFrequency = Integer.parseInt(componentService.getComponentDataProperty(ram, "memoryFrequency"));
				ramCapacity = Integer.parseInt(componentService.getComponentDataProperty(ram, "memoryCapacity"));
				RAMCaseDescription ramCaseDescription = new RAMCaseDescription(ram, ramType, ramFrequency, ramCapacity);

				storage = componentService.getComponentObjectProperty(pc, "hasStorage");
				storageType = componentService.getComponentType(storage);
				storageCapacity = Integer
						.parseInt(componentService.getComponentDataProperty(motherboard, "storageCapacity"));
				StorageCaseDescription storageCaseDescription = new StorageCaseDescription(storage, storageType,
						storageCapacity);

				PCCaseDescription pcCaseDescription = new PCCaseDescription(processorCaseDescription,
						motherboardCaseDescription, gpuCaseDescription, ramCaseDescription, storageCaseDescription);

				cbrCase.setDescription(pcCaseDescription);
				cases.add(cbrCase);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cases;
	}

	@Override
	public void initFromXMLfile(URL file) throws InitializingException {
		// TODO Auto-generated method stub

	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	@Override
	public void storeCases(Collection<CBRCase> cases) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCases(Collection<CBRCase> cases) {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<CBRCase> retrieveAllCases() {
		// TODO Auto-generated method stub
		return null;
	}

}
