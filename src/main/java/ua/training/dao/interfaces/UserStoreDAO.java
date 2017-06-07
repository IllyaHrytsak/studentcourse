package ua.training.dao.interfaces;


/**
 * Data access object for user_store table
 *
 * @author Illya Hrytsak
 */
public interface UserStoreDAO {

    /**
     * Tries to insert mark into course for student
     * @param courseId courseId of course
     * @param userId userId of user
     * @param mark mark
     * @return true if insertion was successful or false if not
     */
    boolean insertMark(Long courseId, Long userId, Integer mark);

    /**
     * Tries to insert student into course
     * @param courseId courseId of course
     * @param userId userId of user
     * @return 1 if insertion was successful or -1 if not
     */
    Long insertStudentIntoCourse(Long courseId, Long userId);

    /**
     * Finds mark by courseId and userId
     * @param courseId courseId of course
     * @param userId userId of user
     * @return value of mark
     */
    Integer findMark(Long courseId, Long userId);

    /**
     * Tries to remove student from course
     * @param courseId courseId of course
     * @param userId userId of user
     * @return true if deletion was successful or false if not
     */
    boolean removeStudent(Long courseId, Long userId);

    boolean removeCourse(Long courseId);

    boolean findLecturer(Long userId);

}
