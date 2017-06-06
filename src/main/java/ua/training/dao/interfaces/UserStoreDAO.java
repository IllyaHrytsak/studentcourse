package ua.training.dao.interfaces;


public interface UserStoreDAO {

    boolean insertMark(Long courseId, Long userId, Integer mark);
    Long insertStudentIntoCourse(Long courseId, Long userId);
    Integer findMark(Long courseId, Long userId);
    boolean removeStudent(Long courseId, Long userId);

}
