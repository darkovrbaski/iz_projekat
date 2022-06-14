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
		fis.setVariable("single_core_clock", computerSpecDTO.getSingleCoreClock());
		fis.setVariable("ram_size", computerSpecDTO.getRamSize());
		fis.setVariable("v_ram_size", computerSpecDTO.getvRamSize());

		fis.evaluate();

		Variable homeUseAnalise = fis.getFunctionBlock("computer_purpose_evaluation").getVariable("home_use");
		Variable gamingAnalise = fis.getFunctionBlock("computer_purpose_evaluation").getVariable("gaming");
		Variable miningAnalise = fis.getFunctionBlock("computer_purpose_evaluation").getVariable("mining");
		Variable hostingAnalise = fis.getFunctionBlock("computer_purpose_evaluation").getVariable("hosting");
		JFuzzyChart.get().chart(homeUseAnalise, homeUseAnalise.getDefuzzifier(), true);
		JFuzzyChart.get().chart(gamingAnalise, gamingAnalise.getDefuzzifier(), true);
		JFuzzyChart.get().chart(miningAnalise, gamingAnalise.getDefuzzifier(), true);
		JFuzzyChart.get().chart(hostingAnalise, gamingAnalise.getDefuzzifier(), true);

		PurposeEvaluationDTO prposeEvaluationDTO = new PurposeEvaluationDTO();
		prposeEvaluationDTO.setHomeUse((double) Math.round(homeUseAnalise.getValue() * 100d) / 100d);
		prposeEvaluationDTO.setGaming((double) Math.round(gamingAnalise.getValue() * 100d) / 100d);
		prposeEvaluationDTO.setMining((double) Math.round(miningAnalise.getValue() * 100d) / 100d);
		prposeEvaluationDTO.setHosting((double) Math.round(hostingAnalise.getValue() * 100d) / 100d);
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
