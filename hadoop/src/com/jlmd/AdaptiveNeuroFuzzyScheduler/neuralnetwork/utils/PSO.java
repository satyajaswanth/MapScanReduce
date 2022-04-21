package com.jlmd.AdaptiveNeuroFuzzyScheduler.neuralnetwork.utils;

import weka.test.java.weka.attributeSelection.*;
import java.util.Random;

import junit.framework.TestCase;
import weka.attributeSelection.PSOBitSet;

/**
 * 
 * @author code
 *
 */
public class PSO extends TestCase {

	/**
	 * Constructor
	 * 
	 * @param name
	 */
	public PSO(String name) {
		super(name);
	}
	protected void setUp() throws Exception {
		super.setUp();
	}
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testAuxThreePBMCX() {
		char[] current, bestG, bestP;
		int i, size = 100, seed, times = 10000;
		int [][] count;
		Random r;
		double w1 = 0.4, w2 = 0.3, w3 = 0.3;
		double avgA, avgB, avgC, totalA, totalB, totalC;
		long resultA, expectedA, resultB, expectedB, resultC, expectedC;
		String result;

		current = new char[size];
		bestG = new char[size];
		bestP = new char[size];

		for (i = 0; i < size; i++) {
			current[i] = 'a';
			bestG[i] = 'b';
			bestP[i] = 'c';
		}

		count = new int[times][3];

		for (seed = 0; seed < times; seed++) {
			r = new Random(seed);
			
			result = auxThreePBMCX(r, w1, w2, w3, current, bestG, bestP);
			
			count[seed] = countABC(result);			
		}
		
		totalA = totalB = totalC = 0.0;
		
		for (i = 0; i < times; i++) {
			totalA = totalA + count[i][0];
			totalB = totalB + count[i][1];
			totalC = totalC + count[i][2];
		}
		
		avgA = totalA / times;
		avgB = totalB / times;
		avgC = totalC / times;
		
		resultA = Math.round(avgA);
		resultB = Math.round(avgB);
		resultC = Math.round(avgC);
		
		expectedA = Math.round(size * w1);
		expectedB = Math.round(size * w2);
		expectedC = Math.round(size * w3);
		
		assertTrue(resultA == expectedA);
		assertTrue(resultB == expectedB);
		assertTrue(resultC == expectedC);		
		
	}
	
	
	public String auxThreePBMCX(Random r, double currentW, double bestGW, double bestpPW, char[] current, char[] bestG, char[] bestP) {
		StringBuffer result = new StringBuffer();
		double p;
		
		for (int i = 0; i < current.length; i++) {
			p = r.nextDouble();
			if (p <= currentW) {
				result.append(current[i]);
			} else if (p <= (currentW + bestGW)) {
				result.append(bestG[i]);
			} else {
				result.append(bestP[i]);
			}			
		}
		
		return result.toString();
	}
	
	
	public int [] countABC(String string) {
		int [] result;
		
		result = new int[3];
		result[0] = 0;
		result[1] = 0;
		result[2] = 0;
		
		for (int i = 0; i < string.length(); i++) {
			if (string.charAt(i) == 'a') {
				result[0]++;
			} else if (string.charAt(i) == 'b') {
				result[1]++;
			} else if (string.charAt(i) == 'c'){
				result[2]++;
			}
		}
		
		return result;
	}
	


	public void testMutation() {
		int i, seed, size = 100, times = 100;
		long result, expected;
		int [] count;
		Random r;
		double total, avg, p = 0.1;
		PSOBitSet tester = new PSOBitSet(size);
		
		count = new int[times];
		
		for (seed = 0; seed < times; seed++) {
			r = new Random(seed);
			
			for (i = 0; i < size; i++) {
				tester.set(i);
			}

			tester.mutation(0, r, p);
			
			count[seed] = size - tester.countOnes();
			
		} 
		total = 0.0;
		for (i = 0; i < times; i++) {
			total = total + count[i];
		}
		
		avg = total / times;		
		result = Math.round(avg);
		expected = Math.round(size * p);
		
		assertTrue(result == expected);
		
	}
	public void testCountOnes() {
		int i, result, size = 10;
		PSOBitSet tester = new PSOBitSet(size);
		
		for (i = 0; i < size; i++) {
			if (i % 2 == 0) {
				tester.set(i);
			}
		}

		result = tester.countOnes();
		
		assertTrue(result == size/2);
		
	}

}
