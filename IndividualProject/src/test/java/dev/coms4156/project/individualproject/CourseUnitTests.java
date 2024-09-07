package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * This class represents the unit test cases for testing the course class.
 */
@SpringBootTest
@ContextConfiguration
public class CourseUnitTests {

  @BeforeAll
  public static void setupCourseForTesting() {
    testCourse = new Course("Griffin Newbold", "417 IAB", "11:40-12:55", 250);
  }

  @Test
  public void enrollStudentTest() {
    boolean expectedResult = true;
    assertEquals(expectedResult, testCourse.enrollStudent());
    testCourse.setEnrolledStudentCount(250);
    expectedResult = false;
    assertEquals(expectedResult, testCourse.enrollStudent());
  }

  @Test
  public void dropStudentTest() {
    boolean expectedResult = true;
    assertEquals(expectedResult, testCourse.dropStudent());
    testCourse.setEnrolledStudentCount(0);
    expectedResult = false;
    assertEquals(expectedResult, testCourse.dropStudent());
  }

  @Test
  public void getCourseLocationTest() {
    String expectedResult = "417 IAB";
    assertEquals(expectedResult, testCourse.getCourseLocation());
    testCourse.reassignLocation("301 URIS");
    expectedResult = "301 URIS";
    assertEquals(expectedResult, testCourse.getCourseLocation());
  }

  @Test
  public void getInstructorNameTest() {
    String expectedResult = "Griffin Newbold";
    assertEquals(expectedResult, testCourse.getInstructorName());
    testCourse.reassignInstructor("Gail Kaiser");
    expectedResult = "Gail Kaiser";
    assertEquals(expectedResult, testCourse.getInstructorName());
  }

  @Test
  public void getCourseTimeSlotTest() {
    String expectedResult = "11:40-12:55";
    assertEquals(expectedResult, testCourse.getCourseTimeSlot());
    testCourse.reassignTime("2:40-3:55");
    expectedResult = "2:40-3:55";
    assertEquals(expectedResult, testCourse.getCourseTimeSlot());
  }

  @Test
  public void toStringTest() {
    String expectedResult = "\nInstructor: Gail Kaiser; Location: 301 URIS; Time: 2:40-3:55";
    assertEquals(expectedResult, testCourse.toString());
  }

  @Test
  public void isCourseFullTest() {
    testCourse.setEnrolledStudentCount(0);
    boolean expectedResult = false;
    assertEquals(expectedResult, testCourse.isCourseFull());
    testCourse.setEnrolledStudentCount(250);
    expectedResult = true;
    assertEquals(expectedResult, testCourse.isCourseFull());
  }

  /** The test course instance used for testing. */
  public static Course testCourse;
}

