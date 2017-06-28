package ua.training.dao.interfaces;


import ua.training.entity.Course;
import ua.training.entity.User;
import java.util.List;

/**
 * Data access object for Course table
 *
 * @author Illya Hrytsak
 */

public interface CourseDAO {

    /**
     * Finds all courses in database
     * @return list of all courses
     */
    List<Course> findAll();

    /**
     * Inserts course into database
     * @param course Course
     * @return true if insertion was successful or false if not
     */
    boolean insertCourse(Course course);

    /**
     * Finds course by course id
     * @param courseId courseId of course
     * @return course
     */
    Course findCourseWithCourseId(Long courseId);

    /**
     * Finds course by course name
     * @param courseName courseName of course
     * @return course
     */
    Course findCourseWithCourseName(String courseName);

    /**
     * Tries to update course in database
     * @param course Course
     * @return true if update was successful or false if not
     */
    boolean updateCourse(Course course);

    /**
     * Finds list of student that studding on this course
     * @param courseId courseIf of course
     * @return list of students
     */
    List<User> findWhereUserIsStudent(Long courseId);

    /**
     * Finds all courses for user with id
     * @param userId userId of user
     * @return list of courses
     */
    List<Course> findStudentCourses(Long userId);

    /**
     * Remove course from course table by courseId
     * @param courseId courseId of course
     * @return true if removing was successful or false if not
     */
    boolean removeCourseByCourseId(Long courseId);



}


