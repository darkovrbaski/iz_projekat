package izproject.service;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.asm.TypeReference;
import org.springframework.stereotype.Service;

import izproject.dto.MalfunctionSpecsDTO;
import izproject.dto.PurposeEvaluationDTO;
import unbbayes.io.BaseIO;
import unbbayes.io.NetIO;
import unbbayes.prs.Node;
import unbbayes.prs.bn.JunctionTreeAlgorithm;
import unbbayes.prs.bn.ProbabilisticNetwork;
import unbbayes.prs.bn.ProbabilisticNode;
import unbbayes.util.extension.bn.inference.IInferenceAlgorithm;

@Service
public class MalfunctionEvaluationService {

	public PurposeEvaluationDTO getEvaluation(MalfunctionSpecsDTO malfunctionSpecsDTO) {
		
		//get symptoms
		List<String> symptom = malfunctionSpecsDTO.getSymptoms();
		String finalSymptom = "";
		for (int i = 0; i < symptom.size(); i++) {
			if(i==symptom.size()-1) {
				finalSymptom = finalSymptom.concat(symptom.get(i));
				System.out.println(finalSymptom);
			}
			else {
				finalSymptom = finalSymptom.concat(symptom.get(i)+"_");
			}
		}
		
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
		//set state
		ProbabilisticNode factNode = (ProbabilisticNode)net.getNode(finalSymptom);
        factNode.addFinding(0);

		
		// propagation
        try {
            net.updateEvidences();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

		// states overview
		List<Node> nodeList = net.getNodes();
		for (Node node : nodeList) {
			System.out.println(node.getName());
			for (int i = 0; i < node.getStatesSize(); i++) {
				System.out.println(node.getStateAt(i) + ": " + ((ProbabilisticNode) node).getMarginalAt(i));
			}
		}
		
		System.out.println();

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
		return null;
	}

}
