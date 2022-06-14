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

	ProbabilisticNetwork net = new ProbabilisticNetwork("faultEvaluation");
	
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
		
		//setComponents
		setComponentInputs(malfunctionSpecsDTO);
		
		
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
	
	private void setComponentInputs(MalfunctionSpecsDTO malfunctionSpecsDTO) {
		if(malfunctionSpecsDTO.getGraphicCard()!=null) {
			ProbabilisticNode componentNode = (ProbabilisticNode) net.getNode("GPU");
			if(malfunctionSpecsDTO.getGraphicCard().toLowerCase().equals("amd")) {
				componentNode.addFinding(0);
			} else if(malfunctionSpecsDTO.getGraphicCard().toLowerCase().equals("nvidia")){
				componentNode.addFinding(1);
			}
		}
		
		if(malfunctionSpecsDTO.getStorageType()!=null) {
			ProbabilisticNode componentNode = (ProbabilisticNode) net.getNode("Storage");
			if(malfunctionSpecsDTO.getStorageType().toLowerCase().equals("hdd")) {
				componentNode.addFinding(0);
			} else if(malfunctionSpecsDTO.getStorageType().toLowerCase().equals("ssd")){
				componentNode.addFinding(1);
			}
		}
		
		if(malfunctionSpecsDTO.getKeyboardCaseType()!=null) {
			ProbabilisticNode componentNode = (ProbabilisticNode) net.getNode("CaseType");
			if(malfunctionSpecsDTO.getKeyboardCaseType().toLowerCase().equals("pc")) {
				componentNode.addFinding(0);
			} else if(malfunctionSpecsDTO.getKeyboardCaseType().toLowerCase().equals("laptop")){
				componentNode.addFinding(1);
			}
		}
		
		if(malfunctionSpecsDTO.getKeyboardType()!=null) {
			ProbabilisticNode componentNode = (ProbabilisticNode) net.getNode("Keyboard");
			if(malfunctionSpecsDTO.getKeyboardType().toLowerCase().equals("wireless")) {
				componentNode.addFinding(0);
			} else if(malfunctionSpecsDTO.getKeyboardType().toLowerCase().equals("wired")){
				componentNode.addFinding(1);
			}
		}
		
		if(malfunctionSpecsDTO.getMouseManufacturer()!=null) {
			ProbabilisticNode componentNode = (ProbabilisticNode) net.getNode("MouseManufacturer");
			if(malfunctionSpecsDTO.getMouseManufacturer().toLowerCase().equals("logitech")) {
				componentNode.addFinding(0);
			} else if(malfunctionSpecsDTO.getMouseManufacturer().toLowerCase().equals("bloody")){
				componentNode.addFinding(1);
			}
		}
		
		if(malfunctionSpecsDTO.getMouseType()!=null) {
			ProbabilisticNode componentNode = (ProbabilisticNode) net.getNode("MouseType");
			if(malfunctionSpecsDTO.getMouseType().toLowerCase().equals("wireless")) {
				componentNode.addFinding(0);
			} else if(malfunctionSpecsDTO.getMouseType().toLowerCase().equals("wired")){
				componentNode.addFinding(1);
			}
		}
		
		if(malfunctionSpecsDTO.getMotherboard()!=null) {
			ProbabilisticNode componentNode = (ProbabilisticNode) net.getNode("Motherboard");
			if(malfunctionSpecsDTO.getMotherboard().toLowerCase().equals("gigabyte")) {
				componentNode.addFinding(0);
			} else if(malfunctionSpecsDTO.getMotherboard().toLowerCase().equals("else")){
				componentNode.addFinding(1);
			}
		}
		
		if(malfunctionSpecsDTO.getAntivirusSoftware()!=null) {
			ProbabilisticNode componentNode = (ProbabilisticNode) net.getNode("AntivirusSoftware");
			if(malfunctionSpecsDTO.getAntivirusSoftware().toLowerCase().equals("yes")) {
				componentNode.addFinding(0);
			} else if(malfunctionSpecsDTO.getAntivirusSoftware().toLowerCase().equals("no")){
				componentNode.addFinding(1);
			}
		}
		
		if(malfunctionSpecsDTO.getOperatingSystem()!=null) {
			ProbabilisticNode componentNode = (ProbabilisticNode) net.getNode("OperatingSystem");
			if(malfunctionSpecsDTO.getOperatingSystem().toLowerCase().equals("windows")) {
				componentNode.addFinding(0);
			} else if(malfunctionSpecsDTO.getOperatingSystem().toLowerCase().equals("linux")){
				componentNode.addFinding(1);
			}
		}
		
		if(malfunctionSpecsDTO.getMemoryType()!=null) {
			ProbabilisticNode componentNode = (ProbabilisticNode) net.getNode("RAM");
			if(malfunctionSpecsDTO.getMemoryType().toLowerCase().equals("ddr3")) {
				componentNode.addFinding(0);
			} else if(malfunctionSpecsDTO.getMemoryType().toLowerCase().equals("ddr4")){
				componentNode.addFinding(1);
			}
		}
		
		if(malfunctionSpecsDTO.getMonitor()!=null) {
			ProbabilisticNode componentNode = (ProbabilisticNode) net.getNode("Monitor");
			if(malfunctionSpecsDTO.getMonitor().toLowerCase().equals("va")) {
				componentNode.addFinding(0);
			} else if(malfunctionSpecsDTO.getMonitor().toLowerCase().equals("tn/ips")){
				componentNode.addFinding(1);
			}else if(malfunctionSpecsDTO.getMonitor().toLowerCase().equals("oled")){
				componentNode.addFinding(2);
			}
		}
	}

}
