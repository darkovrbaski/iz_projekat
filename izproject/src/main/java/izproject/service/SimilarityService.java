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

		String processor = pcSimilarityDTO.getProcessor();
		String motherboard = pcSimilarityDTO.getMotherboard();
		String gpu = pcSimilarityDTO.getGpu();
		String ram = pcSimilarityDTO.getRam();
		String storage = pcSimilarityDTO.getStorage();

		String processorManufacturer = processor.split("_")[0];
		int numOfCores = Integer.parseInt(componentService.getComponentDataProperty(processor, "processorCores"));
		float processorClockSpeed = Float
				.parseFloat(componentService.getComponentDataProperty(processor, "processorClock"));
		ProcessorCaseDescription processorCaseDescription = new ProcessorCaseDescription(processor,
				processorManufacturer, numOfCores, processorClockSpeed);

		String motherboardManufacturer = motherboard.split("_")[0];
		MotherboardCaseDescription motherboardCaseDescription = new MotherboardCaseDescription(motherboard,
				motherboardManufacturer);

		String gpuManufacturer = gpu.split("_")[0];
		int gpuMemory = Integer.parseInt(componentService.getComponentDataProperty(gpu, "graphicCardMemory"));
		float gpuHashRate = Float.parseFloat(componentService.getComponentDataProperty(gpu, "graphicCardHashRate"));
		int gpuClockSpeed = 0;
		GPUCaseDescription gpuCaseDescription = new GPUCaseDescription(gpu, gpuManufacturer, gpuMemory, gpuClockSpeed,
				gpuHashRate);

		String ramType = componentService.getComponentType(ram);
		int ramFrequency = Integer.parseInt(componentService.getComponentDataProperty(ram, "memoryFrequency"));
		int ramCapacity = Integer.parseInt(componentService.getComponentDataProperty(ram, "memoryCapacity"));
		RAMCaseDescription ramCaseDescription = new RAMCaseDescription(ram, ramType, ramFrequency, ramCapacity);

		String storageType = componentService.getComponentType(storage);
		int storageCapacity = Integer
				.parseInt(componentService.getComponentDataProperty(motherboard, "storageCapacity"));
		StorageCaseDescription storageCaseDescription = new StorageCaseDescription(storage, storageType,
				storageCapacity);

		return new PCCaseDescription(processorCaseDescription, motherboardCaseDescription, gpuCaseDescription,
				ramCaseDescription, storageCaseDescription);
	}

}
