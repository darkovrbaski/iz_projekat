package izproject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import es.ucm.fdi.gaia.jcolibri.cbrcore.CBRQuery;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.RetrievalResult;
import izproject.dto.PCSimilarityDTO;
import izproject.similiar.CbrApplication;
import izproject.similiar.model.PCCaseDescription;

@Service
public class SimilarityService {

	public List<PCSimilarityDTO> getSimilarPCs(PCSimilarityDTO pcCaseDescriptionDTO) {
		CbrApplication recommender = new CbrApplication();
		List<PCSimilarityDTO> similarityDTOs = new ArrayList<>();
		try {
			recommender.configure();

			recommender.preCycle();

			CBRQuery query = new CBRQuery();
			PCCaseDescription pcCaseDescription = new PCCaseDescription(
					pcCaseDescriptionDTO.getProcessorCaseDescription(),
					pcCaseDescriptionDTO.getMotherboardCaseDescription(), pcCaseDescriptionDTO.getGpuCaseDescription(),
					pcCaseDescriptionDTO.getRamCaseDescription(), pcCaseDescriptionDTO.getStorageCaseDescription());

			// TODO

			query.setDescription(pcCaseDescription);

			recommender.cycle(query);

			recommender.postCycle();
			PCSimilarityDTO pcSimilarityDTO;
			for (RetrievalResult result : recommender.getEval()) {
				PCCaseDescription pcCaseDescription2 = (PCCaseDescription) result.get_case().getDescription();
				pcSimilarityDTO = new PCSimilarityDTO(pcCaseDescription2.getProcessorCaseDescription(),
						pcCaseDescription2.getMotherboardCaseDescription(), pcCaseDescription2.getGpuCaseDescription(),
						pcCaseDescription2.getRamCaseDescription(), pcCaseDescription2.getStorageCaseDescription(),
						result.getEval());
				similarityDTOs.add(pcSimilarityDTO);
			}
			return similarityDTOs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
