package izproject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ucm.fdi.gaia.jcolibri.cbrcore.CBRQuery;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.RetrievalResult;
import izproject.dto.PCSimilarityDTO;
import izproject.similiar.CbrApplication;
import izproject.similiar.model.GPUCaseDescription;
import izproject.similiar.model.MotherboardCaseDescription;
import izproject.similiar.model.PCCaseDescription;
import izproject.similiar.model.ProcessorCaseDescription;
import izproject.similiar.model.RAMCaseDescription;
import izproject.similiar.model.StorageCaseDescription;

@Service
public class SimilarityService {

	@Autowired
	ComponentService componentService;

	public List<PCSimilarityDTO> getSimilarPCs(PCSimilarityDTO pcSimilarityDTO) {

		CbrApplication recommender = new CbrApplication();
		List<PCSimilarityDTO> similarityDTOs = new ArrayList<>();

		try {
			recommender.configure();

			recommender.preCycle();

			CBRQuery query = new CBRQuery();

			PCCaseDescription pcCaseDescription = extractData(pcSimilarityDTO);
			
			query.setDescription(pcCaseDescription);

			recommender.cycle(query);

			recommender.postCycle();

			PCSimilarityDTO pcSimilarityDTOReturn;

			for (RetrievalResult result : recommender.getEval()) {

				PCCaseDescription pcCaseDescriptionResult = (PCCaseDescription) result.get_case().getDescription();
				pcSimilarityDTOReturn = new PCSimilarityDTO(
						pcCaseDescriptionResult.getProcessorCaseDescription().getName(),
						pcCaseDescriptionResult.getMotherboardCaseDescription().getName(),
						pcCaseDescriptionResult.getGpuCaseDescription().getName(),
						pcCaseDescriptionResult.getRamCaseDescription().getName(),
						pcCaseDescriptionResult.getStorageCaseDescription().getName(), result.getEval());

				similarityDTOs.add(pcSimilarityDTOReturn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return similarityDTOs;
	}

	public PCCaseDescription extractData(PCSimilarityDTO pcSimilarityDTO) {

		String processorManufacturer = "";
		int numOfCores = 4;
		float processorClockSpeed = 2.5f;

		String motherboardManufacturer = "";

		String gpuManufacturer = "";
		int gpuMemory = 3;
		int gpuClockSpeed = 1666;
		float gpuHashRate = 24f;

		String ramType = "";
		int ramFrequency = 2666;
		int ramCapacity = 8;

		String storageType = "";
		int storageCapacity = 1000;

		String processor = pcSimilarityDTO.getCpuName();
		String motherboard = pcSimilarityDTO.getMotherboardName();
		String gpu = pcSimilarityDTO.getGpuName();
		String ram = pcSimilarityDTO.getRamName();
		String storage = pcSimilarityDTO.getStorageName();

		processorManufacturer = processor.split("_")[0];
		if (componentService.getComponentDataProperty(processor, "processorCores") != "")
			numOfCores = Integer.parseInt(componentService.getComponentDataProperty(processor, "processorCores"));
		if (componentService.getComponentDataProperty(processor, "processorClock") != "")
			processorClockSpeed = Float.parseFloat(componentService.getComponentDataProperty(processor, "processorClock"));
		ProcessorCaseDescription processorCaseDescription = new ProcessorCaseDescription(processor,
				processorManufacturer, numOfCores, processorClockSpeed);

		motherboardManufacturer = motherboard.split("_")[0];
		MotherboardCaseDescription motherboardCaseDescription = new MotherboardCaseDescription(motherboard,
				motherboardManufacturer);

		gpuManufacturer = gpu.split("_")[0];
		if (componentService.getComponentDataProperty(gpu, "graphicCardMemory") != "")
			gpuMemory = Integer.parseInt(componentService.getComponentDataProperty(gpu, "graphicCardMemory"));
		if (componentService.getComponentDataProperty(gpu, "graphicCardHashRate") != "")
			gpuHashRate = Float.parseFloat(componentService.getComponentDataProperty(gpu, "graphicCardHashRate"));
		gpuClockSpeed = 0;
		GPUCaseDescription gpuCaseDescription = new GPUCaseDescription(gpu, gpuManufacturer, gpuMemory, gpuClockSpeed,
				gpuHashRate);

		ramType = componentService.getComponentType(ram);
		if (componentService.getComponentDataProperty(ram, "memoryFrequency") != "")
			ramFrequency = Integer.parseInt(componentService.getComponentDataProperty(ram, "memoryFrequency"));
		if (componentService.getComponentDataProperty(ram, "memoryCapacity") != "")
			ramCapacity = Integer.parseInt(componentService.getComponentDataProperty(ram, "memoryCapacity"));
		RAMCaseDescription ramCaseDescription = new RAMCaseDescription(ram, ramType, ramFrequency, ramCapacity);

		storageType = componentService.getComponentType(storage);
		if (componentService.getComponentDataProperty(storage, "storageCapacity") != "")
			storageCapacity = Integer.parseInt(componentService.getComponentDataProperty(storage, "storageCapacity"));
		StorageCaseDescription storageCaseDescription = new StorageCaseDescription(storage, storageType,
				storageCapacity);

		return new PCCaseDescription(processorCaseDescription, motherboardCaseDescription, gpuCaseDescription,
				ramCaseDescription, storageCaseDescription);
	}

}
