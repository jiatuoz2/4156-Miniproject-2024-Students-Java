package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;

/**
 * This class represents the unit test cases for testing the MyFileDatabase class.
 */
@SpringBootTest
@ContextConfiguration
public class RouteControllerUnitTests {

  @BeforeAll
  public static void setupRouteControllerForTesting() {
    testRouteController = new RouteController();
  }

  @Test
  public void indexTest() {
    String expectedResult = "Welcome, in order to make an API call direct your browser or " +
        "Postman to an endpoint \n\n This can be done using the following format: \n\n http:127.0.0"
        + ".1:8080/endpoint?arg=value";
    assertEquals(expectedResult, testRouteController.index());
  }

  @Test
  public void retrieveDepartmentTest() {
    HttpStatus expectedResult = HttpStatus.NOT_FOUND;
    assertEquals(expectedResult, testRouteController.retrieveDepartment("CS").getStatusCode());
    expectedResult = HttpStatus.OK;
    assertEquals(expectedResult, testRouteController.retrieveDepartment("COMS").getStatusCode());
  }

  @Test
  public void retrieveCourseTest() {
    String expectedResult = "Department Not Found";
    assertEquals(expectedResult, testRouteController.retrieveCourse("CS", 1004).getBody());
    expectedResult = "Course Not Found";
    assertEquals(expectedResult, testRouteController.retrieveCourse("COMS", 104).getBody());
    expectedResult = "\nInstructor: Gail Kaiser; Location: 501 NWC; Time: 10:10-11:25";
    assertEquals(expectedResult, testRouteController.retrieveCourse("COMS", 4156).getBody());
  }

  @Test
  public void isCourseFullTest() {
    HttpStatus expectedResult = HttpStatus.NOT_FOUND;
    assertEquals(expectedResult, testRouteController.isCourseFull("COMS", 104).getStatusCode());
    expectedResult = HttpStatus.OK;
    assertEquals(expectedResult, testRouteController.isCourseFull("COMS", 1004).getStatusCode());
  }

  @Test
  public void getMajorCtFromDeptTest() {
    HttpStatus expectedResult = HttpStatus.NOT_FOUND;
    assertEquals(expectedResult, testRouteController.getMajorCtFromDept("CS").getStatusCode());
    expectedResult = HttpStatus.OK;
    assertEquals(expectedResult, testRouteController.getMajorCtFromDept("COMS").getStatusCode());
  }

  @Test
  public void identifyDeptChairTest() {
    HttpStatus expectedResult = HttpStatus.NOT_FOUND;
    assertEquals(expectedResult, testRouteController.identifyDeptChair("CS").getStatusCode());
    expectedResult = HttpStatus.OK;
    assertEquals(expectedResult, testRouteController.identifyDeptChair("COMS").getStatusCode());
  }

  @Test
  public void findCourseLocationTest() {
    HttpStatus expectedResult = HttpStatus.NOT_FOUND;
    assertEquals(expectedResult, testRouteController.findCourseLocation("COMS", 104).getStatusCode());
    expectedResult = HttpStatus.OK;
    assertEquals(expectedResult, testRouteController.findCourseLocation("COMS", 1004).getStatusCode());
  }

  @Test
  public void findCourseInstructorTest() {
    HttpStatus expectedResult = HttpStatus.NOT_FOUND;
    assertEquals(expectedResult, testRouteController.findCourseInstructor("COMS", 104).getStatusCode());
    expectedResult = HttpStatus.OK;
    assertEquals(expectedResult, testRouteController.findCourseInstructor("COMS", 1004).getStatusCode());
  }

  @Test
  public void findCourseTimeTest() {
    HttpStatus expectedResult = HttpStatus.NOT_FOUND;
    assertEquals(expectedResult, testRouteController.findCourseTime("COMS", 104).getStatusCode());
    expectedResult = HttpStatus.OK;
    assertEquals(expectedResult, testRouteController.findCourseTime("COMS", 1004).getStatusCode());
  }

  @Test
  public void addMajorToDeptTest() {
    HttpStatus expectedResult = HttpStatus.NOT_FOUND;
    assertEquals(expectedResult, testRouteController.addMajorToDept("CS").getStatusCode());
    expectedResult = HttpStatus.OK;
    assertEquals(expectedResult, testRouteController.addMajorToDept("COMS").getStatusCode());
  }

  @Test
  public void removeMajorFromDeptTest() {
    HttpStatus expectedResult = HttpStatus.NOT_FOUND;
    assertEquals(expectedResult, testRouteController.removeMajorFromDept("CS").getStatusCode());
    expectedResult = HttpStatus.OK;
    assertEquals(expectedResult, testRouteController.removeMajorFromDept("COMS").getStatusCode());
  }

  @Test
  public void dropStudentTest() {
    HashMap<String, Department> departmentMapping;
    departmentMapping = IndividualProjectApplication.myFileDatabase.getDepartmentMapping();
    HashMap<String, Course> coursesMapping;
    coursesMapping = departmentMapping.get("COMS").getCourseSelection();
    Course requestedCourse = coursesMapping.get(Integer.toString(1004));

    HttpStatus expectedResult = HttpStatus.NOT_FOUND;
    assertEquals(expectedResult, testRouteController.dropStudent("COMS", 104).getStatusCode());

    requestedCourse.setEnrolledStudentCount(10);
    expectedResult = HttpStatus.OK;
    assertEquals(expectedResult, testRouteController.dropStudent("COMS", 1004).getStatusCode());

    requestedCourse.setEnrolledStudentCount(0);
    expectedResult = HttpStatus.BAD_REQUEST;
    assertEquals(expectedResult, testRouteController.dropStudent("COMS", 1004).getStatusCode());
  }

  @Test
  public void setEnrollmentCountTest() {
    HttpStatus expectedResult = HttpStatus.NOT_FOUND;
    assertEquals(expectedResult,
        testRouteController.setEnrollmentCount("COMS", 104, 0).getStatusCode());
    expectedResult = HttpStatus.OK;
    assertEquals(expectedResult,
        testRouteController.setEnrollmentCount("COMS", 1004, 0).getStatusCode());
  }

  @Test
  public void changeCourseTimeTest() {
    HttpStatus expectedResult = HttpStatus.NOT_FOUND;
    assertEquals(expectedResult,
        testRouteController.changeCourseTime("COMS", 104, "2:40-3:55").getStatusCode());
    expectedResult = HttpStatus.OK;
    assertEquals(expectedResult,
        testRouteController.changeCourseTime("COMS", 1004, "2:40-3:55").getStatusCode());
  }

  /** The test RouteController instance used for testing. */
  public static RouteController testRouteController;
}