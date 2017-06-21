package com.Program.MonteCarloSimulation;

/**
 * Created by Ankur on 6/20/2017.
 */
public class App {
   
    public static void main(String[] args) {
    	Simulate simulator=new Simulate();
    	double[] simulationResult = new double[Simulate.NUMBER_OF_SIMULATION];
    	simulationResult = simulator.calculateAggressiveSimulation();
    	calculateAggessiveFinding(simulationResult);
    	simulationResult = simulator.calculateConservativeSimulation();
    	calculateConservativeFinding(simulationResult);
    }
	
    private static void calculateAggessiveFinding(double[] simulationResult) {
		Calculate calculate = new Calculate();
		double aggressiveMedian = calculate.findMedian(simulationResult);
        double aggressiveTop10Percentile = calculate.findTop10Percentile(simulationResult);
        double aggressiveBottom10Percentile =calculate.findBottom10Percentile(simulationResult);
        System.out.printf("%n Aggressive Median     Top 10 Percentile   Bottom 10 Percentile");
        System.out.printf("%n       %.2f         %.2f           %.2f ",aggressiveMedian,aggressiveTop10Percentile,aggressiveBottom10Percentile);		
    }
	
    private static void calculateConservativeFinding(double[] simulationResult) {
		Calculate calculate = new Calculate();
		double conservativeMedian = calculate.findMedian(simulationResult);
        double conservativeTop10Percentile = calculate.findTop10Percentile(simulationResult);
        double conservativeBottom10Percentile =calculate.findBottom10Percentile(simulationResult);
        System.out.printf("%n Conservative Median   Top 10 Percentile   Bottom 10 Percentile");
        System.out.printf("%n       %.2f         %.2f           %.2f ",conservativeMedian,conservativeTop10Percentile,conservativeBottom10Percentile);
    }

    
}

