package izproject.similiar;

import java.net.URL;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

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

	ComponentService componentService = new ComponentService();

	@Override
	public Collection<CBRCase> retrieveSomeCases(CaseBaseFilter filter) {
		return null;
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
		LinkedList<CBRCase> cases = new LinkedList<CBRCase>();

		try {

			List<String> pcs = componentService.getComponents("PersonalComputer");
			String processor = "";
			String motherboard = "";
			String gpu = "";
			String ram = "";
			String storage = "";

			String processorManufacturer = "";
			int numOfCores = 4;
			float processorClockSpeed = 2.5f;

			String motherboardManufacturer = "";

			String gpuManufacturer = "";
			int gpuMemory = 3;
			int gpuClockSpeed = 1666;
			float gpuHashRate = 24;

			String ramType = "";
			int ramFrequency = 2666;
			int ramCapacity = 8;

			String storageType = "";
			int storageCapacity = 1000;

			for (String pc : pcs) {

				CBRCase cbrCase = new CBRCase();

				processor = componentService.getComponentObjectProperty(pc, "hasCPU");
				processorManufacturer = processor.split("_")[0];
				if (componentService.getComponentDataProperty(processor, "processorCores") != "")
					numOfCores = Integer
							.parseInt(componentService.getComponentDataProperty(processor, "processorCores"));
				if (componentService.getComponentDataProperty(processor, "processorClock") != "")
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
				if (componentService.getComponentDataProperty(gpu, "graphicCardMemory") != "")
					gpuMemory = Integer.parseInt(componentService.getComponentDataProperty(gpu, "graphicCardMemory"));

				if (componentService.getComponentDataProperty(gpu, "graphicCardHashRate") != "")
					gpuHashRate = Float
							.parseFloat(componentService.getComponentDataProperty(gpu, "graphicCardHashRate"));

				GPUCaseDescription gpuCaseDescription = new GPUCaseDescription(gpu, gpuManufacturer, gpuMemory,
						gpuClockSpeed, gpuHashRate);

				ram = componentService.getComponentObjectProperty(pc, "hasMemory");
				ramType = componentService.getComponentType(ram);
				if (componentService.getComponentDataProperty(ram, "memoryFrequency") != "")
					ramFrequency = Integer.parseInt(componentService.getComponentDataProperty(ram, "memoryFrequency"));
				if (componentService.getComponentDataProperty(ram, "memoryCapacity") != "")
					ramCapacity = Integer.parseInt(componentService.getComponentDataProperty(ram, "memoryCapacity"));
				RAMCaseDescription ramCaseDescription = new RAMCaseDescription(ram, ramType, ramFrequency, ramCapacity);

				storage = componentService.getComponentObjectProperty(pc, "hasStorage");
				storageType = componentService.getComponentType(storage);
				if (componentService.getComponentDataProperty(storage, "storageCapacity") != "")
					storageCapacity = Integer
							.parseInt(componentService.getComponentDataProperty(storage, "storageCapacity"));
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

}
