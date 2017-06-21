package com.Program.MonteCarloSimulation;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class SimulateTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public SimulateTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( SimulateTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testConservativeCalaculation()
    {	
    	Simulate simulator=new Simulate();
    	double[] simulationResult = new double[Simulate.NUMBER_OF_SIMULATION];
    	simulationResult = simulator.calculateConservativeSimulation();
        assertNotNull(simulationResult);
    }
    
    public void testAggressiveCalaculation()
    {	
    	Simulate simulator=new Simulate();
    	double[] simulationResult = new double[Simulate.NUMBER_OF_SIMULATION];
    	simulationResult = simulator.calculateAggressiveSimulation();
        assertNotNull(simulationResult);
    }
}
