package izproject.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.jena.rdf.model.InvalidListException;
import org.springframework.asm.TypeReference;
import org.springframework.stereotype.Service;

import izproject.dto.MalfunctionSpecsDTO;
import izproject.dto.PurposeEvaluationDTO;
import unbbayes.io.BaseIO;
import unbbayes.io.NetIO;
import unbbayes.prs.Edge;
import unbbayes.prs.Node;
import unbbayes.prs.bn.JunctionTreeAlgorithm;
import unbbayes.prs.bn.PotentialTable;
import unbbayes.prs.bn.ProbabilisticNetwork;
import unbbayes.prs.bn.ProbabilisticNode;
import unbbayes.prs.exception.InvalidParentException;
import unbbayes.util.extension.bn.inference.IInferenceAlgorithm;

@Service
public class MalfunctionEvaluationService {

	ProbabilisticNode initNode(String nodeName, List<String> stateNames, List<Float> probabiltyValues) {

		if (stateNames.size() != probabiltyValues.size())
			throw new InvalidListException();

		ProbabilisticNode node = new ProbabilisticNode();
		node.setName(nodeName);

		for (String stateName : stateNames) {
			node.appendState(stateName);
		}

		PotentialTable prob = node.getProbabilityFunction();
		prob.addVariable(node);

		for (int i = 0; i < probabiltyValues.size(); i++) {
			prob.setValue(i, probabiltyValues.get(i));
		}

		return node;
	}

	public PurposeEvaluationDTO getEvaluation(MalfunctionSpecsDTO malfunctionSpecsDTO) {

		ProbabilisticNetwork probabilisticNets[];
		
		for (int i = 0; i < malfunctionSpecsDTO.getSymptoms().size(); i++) {
			String symptom = malfunctionSpecsDTO.getSymptoms().get(i); 
			ProbabilisticNetwork net = new ProbabilisticNetwork(symptom);
			// loading from file
			BaseIO io = new NetIO();
			try {
				net = (ProbabilisticNetwork) io.load(new File(TypeReference.class.getResource("/bayes/"+symptom+".net").toURI().getPath()));
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			probabilisticNets[i] = net;
		}
		

		ProbabilisticNode var = initNode("symptoms", malfunctionSpecsDTO.getSymptoms(), null);

		net.addNode(var);

		ProbabilisticNode varTaxi = new ProbabilisticNode();
		varTaxi.setName("taxi");
		varTaxi.appendState("blue");
		varTaxi.appendState("green");
		PotentialTable probTaxi = varTaxi.getProbabilityFunction();
		probTaxi.addVariable(varTaxi);
		probTaxi.setValue(0, 0.85f);
		probTaxi.setValue(1, 0.15f);
		net.addNode(varTaxi);

		ProbabilisticNode varWitness = new ProbabilisticNode();
		varWitness.setName("witness");
		varWitness.appendState("blue");
		varWitness.appendState("green");
		net.addNode(varWitness);
		PotentialTable probWitness = varWitness.getProbabilityFunction();
		probWitness.addVariable(varWitness);

		try {
			net.addEdge(new Edge(varTaxi, varWitness));
		} catch (InvalidParentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		probWitness.setValue(0, 0.8f); // taxi is blue & witness observed as blue
		probWitness.setValue(1, 0.2f); // taxi is green & witness observed as blue
		probWitness.setValue(2, 0.2f); // taxi is blue & witness observed as green
		probWitness.setValue(3, 0.8f); // taxi is green & witness observed as green

		// compiling
		IInferenceAlgorithm algorithm = new JunctionTreeAlgorithm();
		algorithm.setNetwork(net);
		algorithm.run();

		// states overview
		List<Node> nodeList = net.getNodes();
		for (Node node : nodeList) {
			System.out.println(node.getName());
			for (int i = 0; i < node.getStatesSize(); i++) {
				System.out.println(node.getStateAt(i) + ": " + ((ProbabilisticNode) node).getMarginalAt(i));
			}
		}

		// adding an evidence
		ProbabilisticNode factNode = (ProbabilisticNode) net.getNode("witness");
		int stateIndex = 1; // index of state "green"
		factNode.addFinding(stateIndex);

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
