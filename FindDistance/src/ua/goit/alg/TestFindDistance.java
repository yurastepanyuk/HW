package ua.goit.alg;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestFindDistance {

  @Test
  public void twoMinValues() {
	int[] input = {1,5,0,7,5,90,3,4,0};
	int expectedDistance = 2;
	int actualDistance = FindDistance.minDistance(input);
	assertEquals(expectedDistance, actualDistance);
  }
  
  @Test
  public void valuesOnStartAndEnd() {
	int[] input = {1,5,7,5,90,3,4,0};
	int expectedDistance = 7;
	int actualDistance = FindDistance.minDistance(input);
	assertEquals(expectedDistance, actualDistance);
  }
  
  @Test
  public void theSameValues() {
	int[] input = {1,1,1,1,1,1,1,1};
	int expectedDistance = 0;
	int actualDistance = FindDistance.minDistance(input);
	assertEquals(expectedDistance, actualDistance);
  }
}