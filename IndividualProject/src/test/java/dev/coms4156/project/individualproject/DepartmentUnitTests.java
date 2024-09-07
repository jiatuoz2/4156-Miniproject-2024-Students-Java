package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * This class represents the unit test cases for testing the department class.
 */
@SpringBootTest
@ContextConfiguration
public class DepartmentUnitTests {

  @BeforeAll
  public static void setupDepartmentForTesting() {
    //data for coms dept
    Course coms1004 = new Course("Adam Cannon", locations[0], times[0], 400);
    coms1004.setEnrolledStudentCount(249);
    courses = new HashMap<>();
    courses.put("1004", coms1004);
    testDepartment = new Department("COMS", courses, "Luca Carloni", 2700);
    testDepartment2 = new Department("COMS", new HashMap<>(), "Luca Carloni", 2700);
  }

  @Test
  public void getNumberOfMajorsTest() {
    int expectedResult = 2700;
    assertEquals(expectedResult, testDepartment.getNumberOfMajors());
    testDepartment.addPersonToMajor();
    expectedResult = 2701;
    assertEquals(expectedResult, testDepartment.getNumberOfMajors());
    testDepartment.dropPersonFromMajor();
    expectedResult = 2700;
    assertEquals(expectedResult, testDepartment.getNumberOfMajors());
    for (int i = 0; i < 2701; i++) {
      testDepartment.dropPersonFromMajor();
    }
    expectedResult = 0;
    assertEquals(expectedResult, testDepartment.getNumberOfMajors());
  }

  @Test
  public void getDepartmentChairTest() {
    String expectedResult = "Luca Carloni";
    assertEquals(expectedResult, testDepartment.getDepartmentChair());
  }

  @Test
  public void getCourseSelectionTest() {
    int expectedResult = 1;
    assertEquals(expectedResult, testDepartment.getCourseSelection().size());
    Course coms4156 = new Course("Gail Kaiser", "501 NWC", times[2], 120);
    coms4156.setEnrolledStudentCount(109);
    testDepartment.addCourse("4156", coms4156);
    courses.put("4156", coms4156);
    expectedResult = 2;
    assertEquals(expectedResult, testDepartment.getCourseSelection().size());
    testDepartment.createCourse("3134", "Brian Borowski", locations[2], times[1], 250);
    expectedResult = 3;
    assertEquals(expectedResult, testDepartment.getCourseSelection().size());
  }

  @Test
  public void toStringTest() {
    String expectedResult = "COMS 1004: \nInstructor: Adam Cannon; Location: 417 IAB; Time: " +
        "11:40-12:55\n";
    assertEquals(expectedResult, testDepartment.toString());
    expectedResult = "";
    assertEquals(expectedResult, testDepartment2.toString());
  }

  /** The test course instance used for testing. */
  public static String[] times = {"11:40-12:55", "4:10-5:25", "10:10-11:25", "2:40-3:55"};
  public static String[] locations = {"417 IAB", "309 HAV", "301 URIS"};
  public static HashMap<String, Course> courses;
  public static Department testDepartment;
  public static Department testDepartment2;
}