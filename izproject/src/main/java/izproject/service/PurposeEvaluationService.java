package izproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import izproject.dto.ComponentsPurposeEvaluationDTO;
import izproject.dto.ComputerSpecDTO;
import izproject.dto.PurposeEvaluationDTO;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

@Service
public class PurposeEvaluationService {

	@Autowired
	private ComponentService componentService;

	public PurposeEvaluationDTO getPurposeEvaluationComponents(ComponentsPurposeEvaluationDTO components) {
		int numberOfCores = Integer.parseInt(componentService.getComponentDataProperty(components.getCpuName(), "processorCores"));
		double singleCoreClock = Double.parseDouble(componentService.getComponentDataProperty(components.getCpuName(), "processorClock"));
		int ramSize = Integer.parseInt(componentService.getComponentDataProperty(components.getRamName(), "memoryCapacity"));
		double vRamSize = Double.parseDouble(componentService.getComponentDataProperty(components.getGpuName(), "graphicCardMemory"));
		double gpuHashRate = Double.parseDouble(componentService.getComponentDataProperty(components.getGpuName(), "graphicCardHashRate"));
		ComputerSpecDTO computerSpecDTO = new ComputerSpecDTO(numberOfCores, singleCoreClock, ramSize, vRamSize,gpuHashRate);
		return getPurposeEvaluation(computerSpecDTO);
	}

	public PurposeEvaluationDTO getPurposeEvaluation(ComputerSpecDTO computerSpecDTO) {
		FIS fis = loadFCL("/fcl/computer_purpose_evaluation.fcl");

		JFuzzyChart.get().chart(fis.getFunctionBlock("computer_purpose_evaluation"));

		fis.setVariable("number_of_cores", computerSpecDTO.getNumberOfCores());
		fis.setVariable("single_core_clock", computerSpecDTO.getSingleCoreClock());
		fis.setVariable("ram_size", computerSpecDTO.getRamSize());
		fis.setVariable("v_ram_size", computerSpecDTO.getvRamSize());
		fis.setVariable("gpu_hash_rate", computerSpecDTO.getGpuHashRate());

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

	private FIS loadFCL(String fileName) {
		FIS fis = FIS.load(this.getClass().getResourceAsStream(fileName), true);
		if (fis == null) {
			System.err.println("Can't load file: " + fileName);
			System.exit(1);
		}
		return fis;
	}

}
