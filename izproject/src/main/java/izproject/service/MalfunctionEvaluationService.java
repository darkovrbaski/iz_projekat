package izproject.service;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.asm.TypeReference;
import org.springframework.stereotype.Service;

import izproject.dto.BayesEvaluationDTO;
import izproject.dto.MalfunctionSpecsDTO;
import unbbayes.io.BaseIO;
import unbbayes.io.NetIO;
import unbbayes.prs.Node;
import unbbayes.prs.bn.JunctionTreeAlgorithm;
import unbbayes.prs.bn.ProbabilisticNetwork;
import unbbayes.prs.bn.ProbabilisticNode;
import unbbayes.util.extension.bn.inference.IInferenceAlgorithm;

@Service
public class MalfunctionEvaluationService {

	public List<BayesEvaluationDTO> getEvaluation(MalfunctionSpecsDTO malfunctionSpecsDTO) {

		// get symptoms
		List<String> symptom = malfunctionSpecsDTO.getSymptoms();
		String finalSymptom1 = "";
		String finalSymptom2 = "";
		String symptomCause = "";
		for (int i = 0; i < symptom.size(); i++) {
			if (i == symptom.size() - 1) {
				finalSymptom1 = finalSymptom1.concat(symptom.get(i));
			} else {
				finalSymptom1 = finalSymptom1.concat(symptom.get(i) + "_");
			}
		}

		for (int i = symptom.size() - 1; i > -1; i--) {
			if (i == 1) {
				finalSymptom2 = finalSymptom2.concat(symptom.get(i)+ "_");
			} else {
				finalSymptom2 = finalSymptom2.concat(symptom.get(i));
			}
		}

		symptomCause = finalSymptom1.concat("_Cause");

		ProbabilisticNetwork net = new ProbabilisticNetwork("faultEvaluation");
		
		// loading from file
		BaseIO io = new NetIO();
		try {
			net = (ProbabilisticNetwork) io
					.load(new File(TypeReference.class.getResource("/bayes/bayes.net").toURI().getPath()));
		} catch (IOException | URISyntaxException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		// compile
		IInferenceAlgorithm algorithm = new JunctionTreeAlgorithm();
		algorithm.setNetwork(net);
		algorithm.run();
		// set state
		ProbabilisticNode symptomNode = (ProbabilisticNode) net.getNode(finalSymptom1);
		symptomNode.addFinding(0);
		if (symptom.size() > 1) {
			ProbabilisticNode symptomNode2 = (ProbabilisticNode) net.getNode(finalSymptom2);
			symptomNode2.addFinding(0);
		}
		
		if(malfunctionSpecsDTO.getGraphicCard()!=null) {
			ProbabilisticNode gpuNode = (ProbabilisticNode) net.getNode("GPU");
			if(malfunctionSpecsDTO.getGraphicCard().toLowerCase().equals("amd")) {
				gpuNode.addFinding(0);
			} else if(malfunctionSpecsDTO.getGraphicCard().toLowerCase().equals("nvidia")){
				gpuNode.addFinding(1);
			}
		}
		
		if(malfunctionSpecsDTO.getStorageType()!=null) {
			ProbabilisticNode gpuNode = (ProbabilisticNode) net.getNode("Storage");
			if(malfunctionSpecsDTO.getStorageType().toLowerCase().equals("hdd")) {
				gpuNode.addFinding(0);
			} else if(malfunctionSpecsDTO.getStorageType().toLowerCase().equals("ssd")){
				gpuNode.addFinding(1);
			}
		}

		// states overview
		List<Node> nodeList = net.getNodes();

		// propagation
		try {
			net.updateEvidences();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// states overview after propagation
		for (Node node : nodeList) {
			System.out.println(node.getName());
			for (int i = 0; i < node.getStatesSize(); i++) {
				System.out.println(node.getStateAt(i) + ": " + ((ProbabilisticNode) node).getMarginalAt(i));
			}
		}

		List<BayesEvaluationDTO> bayesEvaluationDTOs = new ArrayList<>();
		ProbabilisticNode outputNode = (ProbabilisticNode) net.getNode(symptomCause);
		for (int i = 0; i < outputNode.getStatesSize(); i++) {
			bayesEvaluationDTOs.add(new BayesEvaluationDTO(outputNode.getStateAt(i),
					((ProbabilisticNode) outputNode).getMarginalAt(i)));
		}
		return bayesEvaluationDTOs;
	}

}
