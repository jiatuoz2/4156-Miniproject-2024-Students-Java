package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

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