/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.math.BigDecimal;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import project.Project; 
/**
 * 
 * @author vasug
 */
public class ProjectClassTest {
    private ArrayList<Long> vals = new ArrayList<>();
    
    private Project proj = new Project();
    
    // Populating the array with own values
    @Before
    public void setUp() {
        vals.add((long) 1);
        vals.add((long) 2);
        vals.add((long) 3);
        vals.add((long) 4);
        vals.add((long) 5);
        vals.add((long) 6);        
    }    
    
    @Test
    public void minAssessedValTest(){
        long expected = 1;
        long actual = proj.minAssessedVal(vals);
        assertEquals("Checking the min val function", expected, actual);
    }

    @Test
    public void maxAssessedValTest(){
        long expected = 6;
        long actual = proj.maxAssessedVal(vals);
        assertEquals("Checking the max val function", expected, actual);
    }
    @Test
    public void calcMeanTest(){
        BigDecimal expected = new BigDecimal("3.5");
        BigDecimal actual = proj.calcMean(vals);
        assertTrue(expected.compareTo(actual) == 0); 
    }
    @Test
    public void calcStdDevTest(){
        double expected = 1.870828693387; 
        BigDecimal mean = proj.calcMean(vals);
        System.out.println(mean);
        double actual = proj.calcStandardDeviation(vals, mean);
        assertEquals("Checking the stdDev val function", expected, actual, 0.0000001);
    }
    @Test
    public void calcMedian(){
        double expected = 3.5;
        double actual = proj.calculateMedian(vals);
        assertEquals("Checking the stdDev val function", expected, actual, 0.0000001);
    }
}
