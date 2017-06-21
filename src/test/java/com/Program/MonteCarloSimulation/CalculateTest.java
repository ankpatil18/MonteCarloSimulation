package com.Program.MonteCarloSimulation;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class CalculateTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public CalculateTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( CalculateTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testConservativeMedian()
    {	
    	Simulate simulator=new Simulate();
    	double[] simulationResult = new double[Simulate.NUMBER_OF_SIMULATION];
    	simulationResult = simulator.calculateConservativeSimulation();
        Calculate calculate=new Calculate();
        double median =calculate.findMedian(simulationResult);
        assertTrue(median<=230000 && median>=190000);
    }
    
    public void testConservativeTop10Percentile()
    {	
    	Simulate simulator=new Simulate();
    	double[] simulationResult = new double[Simulate.NUMBER_OF_SIMULATION];
    	simulationResult = simulator.calculateConservativeSimulation();
        Calculate calculate=new Calculate();
        double top10Percentile =calculate.findTop10Percentile(simulationResult);
        assertTrue(top10Percentile<=280000 && top10Percentile>=230000);
    }
     
    public void testConservativeBottom10Percentile()
    {	
    	Simulate simulator=new Simulate();
    	double[] simulationResult = new double[Simulate.NUMBER_OF_SIMULATION];
    	simulationResult = simulator.calculateConservativeSimulation();
        Calculate calculate=new Calculate();
        double bottom10Percentile =calculate.findBottom10Percentile(simulationResult);
        assertTrue(bottom10Percentile>=110000 && bottom10Percentile<=160000);
    }
    
    public void testAggressiveMedian()
    {	
    	Simulate simulator=new Simulate();
    	double[] simulationResult = new double[Simulate.NUMBER_OF_SIMULATION];
    	simulationResult = simulator.calculateAggressiveSimulation();
        Calculate calculate=new Calculate();
        double median =calculate.findMedian(simulationResult);
        assertTrue(median<=210000 && median>=180000);
    }
    
    public void testAggressiveTop10Percentile()
    {	
    	Simulate simulator=new Simulate();
    	double[] simulationResult = new double[Simulate.NUMBER_OF_SIMULATION];
    	simulationResult = simulator.calculateAggressiveSimulation();
        Calculate calculate=new Calculate();
        double top10Percentile =calculate.findTop10Percentile(simulationResult);
        System.out.println(top10Percentile);
        assertTrue(top10Percentile<=320000 && top10Percentile>=290000);
    }
    

    public void testAggressiveBottom10Percentile()
    {	
    	Simulate simulator=new Simulate();
    	double[] simulationResult = new double[Simulate.NUMBER_OF_SIMULATION];
    	simulationResult = simulator.calculateAggressiveSimulation();
        Calculate calculate=new Calculate();
        double bottom10Percentile =calculate.findBottom10Percentile(simulationResult);
        System.out.println(bottom10Percentile);
        assertTrue(bottom10Percentile>=90000 && bottom10Percentile<=120000);
    }
}
