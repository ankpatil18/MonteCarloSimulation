package com.Program.MonteCarloSimulation;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Simulate{
	 	public static final Double USER_INITIAL_AMOUNT = 100000.00;
	    public static final Integer NUMBER_OF_SIMULATION = 10000;
	    public static final Integer NUMBER_OF_YEARS= 20;
	    public static final Double AGGRESSIVE_MEAN_RETURN= 9.4324;
	    public static final Double AGGRESSIVE_RISK_STANDARD_DEVIATION= 15.675;
	    public static final Double CONSERVATIVE_MEAN_RETURN= 6.189;
	    public static final Double CONSERVATIVE_RISK_STANDARD_DEVIATION= 6.3438;
	    public static final Double NORMALIZING_FACTOR = 0.005;
	    public static final Double RISK_AVERSION_AGGRESIVE_COEFFICENT = 4.0;
	    public static final Double RISK_AVERSION_CONSERVATIVE_COEFFICENT = 2.0;
	    public static final Double INFLATION_RATE = 3.5;

	    public Simulate(){}

	    public double[] startSimulation(double[] simulationResult,int meanRate,double meanReturn, double riskStandardDeviation, double riskAversionFactor){
	        Random random = new Random();
	        for(int i=0;i<NUMBER_OF_SIMULATION;i++){
	            double meanReturnRandom = ThreadLocalRandom.current().nextInt(-meanRate,meanRate) * meanReturn;
	            double riskStandardDeveiationRandom =  random.nextGaussian() * riskStandardDeviation;
	            double utilityRate = meanReturnRandom  - NORMALIZING_FACTOR * Math.pow(riskStandardDeveiationRandom,2) * riskAversionFactor;
	            simulationResult[i] = getResult(utilityRate);
	        }
	       return simulationResult;
	    }

	    public static Double getResult(double utilityRate) {
	        double result=USER_INITIAL_AMOUNT;
	        for(int i=0;i<NUMBER_OF_YEARS;i++){
	            double eachYearAmount = result + (result * (utilityRate/100));
	            double afterInflation = eachYearAmount * (INFLATION_RATE/100);
	            result += afterInflation;
	        }
	        return result;
	    }
	    public double[] calculateConservativeSimulation() {
	    	Simulate simulator=new Simulate();
	        double[] conservativeSimulationResult = new double[NUMBER_OF_SIMULATION];
	        int meanRate = 6;
	        conservativeSimulationResult = simulator.startSimulation(conservativeSimulationResult, meanRate, CONSERVATIVE_MEAN_RETURN,CONSERVATIVE_RISK_STANDARD_DEVIATION, RISK_AVERSION_CONSERVATIVE_COEFFICENT);
	        Arrays.sort(conservativeSimulationResult);
			return conservativeSimulationResult;
	    }

	    public double[] calculateAggressiveSimulation() {
	    	Simulate simulator=new Simulate();
	    	int meanRate =9;
	    	double[] aggressiveSimulationResult = new double[NUMBER_OF_SIMULATION];
	        aggressiveSimulationResult = simulator.startSimulation(aggressiveSimulationResult,meanRate,AGGRESSIVE_MEAN_RETURN,AGGRESSIVE_RISK_STANDARD_DEVIATION,RISK_AVERSION_AGGRESIVE_COEFFICENT);
	        Arrays.sort(aggressiveSimulationResult);
			return aggressiveSimulationResult; 
	    }
}
