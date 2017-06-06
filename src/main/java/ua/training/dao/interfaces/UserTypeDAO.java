package ua.training.dao.interfaces;

import ua.training.entity.UserType;
import java.util.List;

/**
 * Data access object for user_type table
 *
 * @author Illya Hrytsak
 */
public interface UserTypeDAO {

    /**
     * Finds all user types in database
     * @return list of all user types
     */
    List<UserType> findAll();

    /**
     * Tries to insert user type in database
     * @param userType userType
     * @return userTypeId of inserted user type
     */
    Long insertUserType(UserType userType);

    /**
     * Finds user type by userTypeId
     * @param userTypeId userTypeId of userType
     * @return userType
     */
    UserType findUserTypeWithUserTypeId(Long userTypeId);

    /**
     * Finds user type by userTypeName
     * @param userTypeName userTypeName of userType
     * @return userType
     */
    UserType findUserTypeWithUserTypeName(String userTypeName);

    /**
     * Tries to update user type in database
     * @param userType userType
     * @return true if update was successful or false if not
     */
    boolean updateUserType(UserType userType);

}
