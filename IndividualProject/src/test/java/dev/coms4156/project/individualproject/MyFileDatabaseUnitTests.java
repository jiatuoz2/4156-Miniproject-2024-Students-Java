package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * This class represents the unit test cases for testing the MyFileDatabase class.
 */
@SpringBootTest
@ContextConfiguration
public class MyFileDatabaseUnitTests {

  /**
   * This method set up the testMyFileDatabase instances for testing.
   */
  @BeforeAll
  public static void setupMyFileDatabaseForTesting() {
    testMyFileDatabase = new MyFileDatabase(0, "./data.txt");
    testMyFileDatabase2 = new MyFileDatabase(0, "./data.txt");

    int expectedResult = 7;
    assertEquals(expectedResult, testMyFileDatabase.getDepartmentMapping().size());
    assertEquals(expectedResult, testMyFileDatabase2.getDepartmentMapping().size());
  }

  @Test
  public void toStringTest() {
    //data for coms dept
    Course coms1004 = new Course("Adam Cannon", locations[0], times[0], 400);
    coms1004.setEnrolledStudentCount(249);
    Map<String, Course> courses = new HashMap<>();
    courses.put("1004", coms1004);
    Department compSci = new Department("COMS", courses, "Luca Carloni", 2700);
    Map<String, Department> mapping = new HashMap<>();
    mapping.put("COMS", compSci);

    testMyFileDatabase2.setMapping(mapping);
    String expectedResult = "For the COMS department: \n"
        + "COMS 1004: \nInstructor: Adam Cannon; Location: 417 IAB; Time: "
        + "11:40-12:55\n";
    assertEquals(expectedResult, testMyFileDatabase2.toString());
  }

  @Test
  public void serializeTest() {
    //data for coms dept
    Course coms1004 = new Course("Adam Cannon", locations[0], times[0], 400);
    coms1004.setEnrolledStudentCount(249);
    Map<String, Course> courses = new HashMap<>();
    courses.put("1004", coms1004);
    Department compSci = new Department("COMS", courses, "Luca Carloni", 2700);
    Map<String, Department> mapping = new HashMap<>();
    mapping.put("COMS", compSci);

    int expectedResult = 1;
    testMyFileDatabase2.setMapping(mapping);
    testMyFileDatabase2.saveContentsToFile();
    assertEquals(expectedResult, testMyFileDatabase.deSerializeObjectFromFile().size());
  }

  /** The test myFileDatabase instance used for testing. */
  public static MyFileDatabase testMyFileDatabase;
  public static MyFileDatabase testMyFileDatabase2;
  public static String[] times = {"11:40-12:55", "4:10-5:25", "10:10-11:25", "2:40-3:55"};
  public static String[] locations = {"417 IAB", "309 HAV", "301 URIS"};
}

