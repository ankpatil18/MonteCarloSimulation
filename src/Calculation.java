import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Ankur on 6/20/2017.
 */
public class Calculation {
    public static final Integer USER_INITIAL_AMOUNT = 100000;
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

    public Calculation(){}

    public static double[] startSimulation(double[] simulationResult,int meanRate,double meanReturn, double riskStandardDeviation, double riskAversionFactor){
        Random random = new Random();
        for(int i=0;i<NUMBER_OF_SIMULATION;i++){
            double meanReturnRandom = ThreadLocalRandom.current().nextInt(-meanRate,meanRate) * meanReturn;
            double riskStandardDeveiationRandom =  random.nextGaussian() * riskStandardDeviation;
            //utility =  meanReturn * normalizingFator * (risk* risk) *riskAversion Coefficent;
            double utilityRate = meanReturnRandom  - NORMALIZING_FACTOR * Math.pow(riskStandardDeveiationRandom,2) * riskAversionFactor;
            simulationResult[i] = getResult(utilityRate);
        }
       return simulationResult;
    }

    private static double findTop10Percentile(double[] simulationResult) {
        int lengthofTop10Percentile=  simulationResult.length - simulationResult.length/10;
        double sum =0;
        for(int i=simulationResult.length-1;i>lengthofTop10Percentile;i--){
            sum +=simulationResult[i];
        }
        double mean = sum / (simulationResult.length/10);
        return mean;

    }

    private static double findBottom10Percentile(double[] simulationResult) {
        int lengthofTop10Percentile=  simulationResult.length/10;
        double sum =0;
        for(int i=0;i<lengthofTop10Percentile;i++){
            sum +=simulationResult[i];
        }
        double mean = sum/lengthofTop10Percentile;
        return mean;
    }

    private static double findMedian(double[] simulationResult) {
        double median = (simulationResult[simulationResult.length/2] + simulationResult[simulationResult.length/2 +1])/2;
        return median;
    }

    public static Double getResult(double utilityRate) {
        double result=(double) USER_INITIAL_AMOUNT;
        for(int i=0;i<NUMBER_OF_YEARS;i++){
            double eachYearAmount = result + (result * (utilityRate/100));
            double afterInflation = eachYearAmount * (INFLATION_RATE/100);
            result += afterInflation;
        }
        return result;
    }
    public static void main(String[] args) {
        calculateAggressiveSimulation();
        calculateConservativeSimulation();
    }

    private static void calculateConservativeSimulation() {
        double[] conservativeSimulationResult = new double[NUMBER_OF_SIMULATION];
        int meanRate = 6;
        conservativeSimulationResult = Calculation.startSimulation(conservativeSimulationResult, meanRate, CONSERVATIVE_MEAN_RETURN,CONSERVATIVE_RISK_STANDARD_DEVIATION, RISK_AVERSION_CONSERVATIVE_COEFFICENT);
        Arrays.sort(conservativeSimulationResult);
        double conservativeMedian = findMedian(conservativeSimulationResult);
        double conservativeTop10Percentile = findTop10Percentile(conservativeSimulationResult);
        double conservativeBottom10Percentile =findBottom10Percentile(conservativeSimulationResult);
        System.out.printf("%n Conservative Median   Top 10 Percentile   Bottom 10 Percentile");
        System.out.printf("%n       %.2f         %.2f           %.2f ",conservativeMedian,conservativeTop10Percentile,conservativeBottom10Percentile);
    }

    private static void calculateAggressiveSimulation() {
        double[] aggressiveSimulationResult = new double[NUMBER_OF_SIMULATION];
        int meanRate = 9;
        aggressiveSimulationResult = Calculation.startSimulation(aggressiveSimulationResult,meanRate,AGGRESSIVE_MEAN_RETURN,AGGRESSIVE_RISK_STANDARD_DEVIATION,RISK_AVERSION_AGGRESIVE_COEFFICENT);
        Arrays.sort(aggressiveSimulationResult);
        double aggressiveMedian = findMedian(aggressiveSimulationResult);
        double aggressiveTop10Percentile = findTop10Percentile(aggressiveSimulationResult);
        double aggressiveBottom10Percentile =findBottom10Percentile(aggressiveSimulationResult);
        System.out.printf("%n Aggressive Median     Top 10 Percentile   Bottom 10 Percentile");
        System.out.printf("%n       %.2f         %.2f           %.2f ",aggressiveMedian,aggressiveTop10Percentile,aggressiveBottom10Percentile);
    }
}
