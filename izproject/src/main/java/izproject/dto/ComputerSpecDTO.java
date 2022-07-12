package izproject.dto;

public class ComputerSpecDTO {

	private int numberOfCores;
	private double singleCoreClock;
	private int ramSize;
	private double vRamSize;
	private double gpuHashRate;
	
	public ComputerSpecDTO(int numberOfCores, double singleCoreClock, int ramSize, double vRamSize,
			double gpuHashRate) {
		super();
		this.numberOfCores = numberOfCores;
		this.singleCoreClock = singleCoreClock;
		this.ramSize = ramSize;
		this.vRamSize = vRamSize;
		this.gpuHashRate = gpuHashRate;
	}

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
