package izproject.dto;

import java.util.List;

public class MalfunctionSpecsDTO {

	private String processor;
	private String memoryType;
	private String memorySize;
	private String graphicCard;
	private List<String> symptoms;

	public List<String> getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(List<String> symptoms) {
		this.symptoms = symptoms;
	}

	public String getProcessor() {
		return processor;
	}

	public void setProcessor(String processor) {
		this.processor = processor;
	}

	public String getMemoryType() {
		return memoryType;
	}

	public void setMemoryType(String memoryType) {
		this.memoryType = memoryType;
	}

	public String getMemorySize() {
		return memorySize;
	}

	public void setMemorySize(String memorySize) {
		this.memorySize = memorySize;
	}

	public String getGraphicCard() {
		return graphicCard;
	}

	public void setGraphicCard(String graphicCard) {
		this.graphicCard = graphicCard;
	}

}
