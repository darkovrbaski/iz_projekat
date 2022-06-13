package izproject.dto;

public class PurposeEvaluationDTO {

	private double homeUse;
	private double gaming;
	private double mining;
	private double hosting;

	public double getHomeUse() {
		return homeUse;
	}

	public void setHomeUse(double homeUse) {
		this.homeUse = homeUse;
	}

	public double getGaming() {
		return gaming;
	}

	public void setGaming(double gaming) {
		this.gaming = gaming;
	}

	public double getMining() {
		return mining;
	}

	public void setMining(double mining) {
		this.mining = mining;
	}

	public double getHosting() {
		return hosting;
	}

	public void setHosting(double hosting) {
		this.hosting = hosting;
	}

}
