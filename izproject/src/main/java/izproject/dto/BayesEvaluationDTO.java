package izproject.dto;

public class BayesEvaluationDTO {

	private String output;
	private double percentage;

	public BayesEvaluationDTO(String output, double percentage) {
		super();
		this.output = output;
		this.percentage = percentage;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

}
