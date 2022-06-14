package izproject.dto;

public class ComputerSpecDTO {

	private int numberOfCores;
	private double singleCoreClock;
	private int ramSize;
	private double vRamSize;
	private double gpuHashRate;

	public int getNumberOfCores() {
		return numberOfCores;
	}

	public double getSingleCoreClock() {
		return singleCoreClock;
	}

	public int getRamSize() {
		return ramSize;
	}

	public double getvRamSize() {
		return vRamSize;
	}

	public double getGpuHashRate() {
		return gpuHashRate;
	}

}
