package com.Program.MonteCarloSimulation;

public class Calculate  implements ModifiedTheoryHandler {
	
	public double findMedian(double[] simulationResult) {
        double median = (simulationResult[simulationResult.length/2] + simulationResult[simulationResult.length/2 +1])/2;
        return median;
    }
	
	public double findBottom10Percentile(double[] simulationResult) {
        int lengthofTop10Percentile=  simulationResult.length/10;
        double sum =0;
        for(int i=0;i<lengthofTop10Percentile;i++){
            sum +=simulationResult[i];
        }
        double mean = sum/lengthofTop10Percentile;
        return mean;
    }
	
	public double findTop10Percentile(double[] simulationResult) {
        int lengthofTop10Percentile=  simulationResult.length - simulationResult.length/10;
        double sum =0;
        for(int i=simulationResult.length-1;i>lengthofTop10Percentile;i--){
            sum +=simulationResult[i];
        }
        double mean = sum / (simulationResult.length/10);
        return mean;
    }
}
