package com.Program.MonteCarloSimulation;

public interface ModifiedTheoryHandler {
	public double findMedian(double[] simulationResult);
	public double findTop10Percentile(double[] simulationResult) ;
	public double findBottom10Percentile(double[] simulationResult);
}
