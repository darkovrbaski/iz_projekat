package izproject.service;

import org.springframework.stereotype.Service;

import izproject.dto.ComputerSpecDTO;
import izproject.dto.PurposeEvaluationDTO;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

@Service
public class PurposeEvaluationService {

	public PurposeEvaluationDTO getPurposeEvaluation(ComputerSpecDTO computerSpecDTO) {
		FIS fis = loadFCL();

		JFuzzyChart.get().chart(fis.getFunctionBlock("computer_purpose_evaluation"));

		fis.setVariable("number_of_cores", computerSpecDTO.getNumberOfCores());

		fis.evaluate();

		Variable analise = fis.getFunctionBlock("computer_purpose_evaluation").getVariable("home_use");
		JFuzzyChart.get().chart(analise, analise.getDefuzzifier(), true);

		PurposeEvaluationDTO prposeEvaluationDTO = new PurposeEvaluationDTO();
		prposeEvaluationDTO.setHomeUse((double) Math.round(analise.getValue() * 100d) / 100d);
		return prposeEvaluationDTO;
	}

	private FIS loadFCL() {
		FIS fis = FIS.load("fcl/computer_purpose_evaluation.fcl", true);
		if (fis == null) {
			System.err.println("Can't load file");
			System.exit(1);
		}
		return fis;
	}

}
