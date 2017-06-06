package ua.training.dao.interfaces;


import ua.training.entity.Course;
import ua.training.entity.User;
import java.util.List;

public interface CourseDAO {

    List<Course> findAll();
    Long insertCourse(Course course);
    Course findCourseWithCourseId(Long courseId);
    Course findCourseWithCourseName(String courseName);
    boolean updateCourse(Course course);
    List<User> findWhereUserIsStudent(Long courseId);
    List<Course> findStudentCourses(Long userId);

}
