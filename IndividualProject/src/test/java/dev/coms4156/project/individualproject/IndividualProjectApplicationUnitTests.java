package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * This class represents the unit test cases for testing the IndividualProjectApplication class.
 */
@SpringBootTest
@ContextConfiguration
public class IndividualProjectApplicationUnitTests {
  @Test
  public void resetDataFileTest() {
    String[] args = new String[1];
    args[0] = "setup";
    IndividualProjectApplication.main(args);

    int expectedResult = 7;
    assertEquals(expectedResult,
        IndividualProjectApplication.myFileDatabase.getDepartmentMapping().size());
  }
}