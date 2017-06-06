package ua.training.dao.interfaces;

import ua.training.entity.UserType;
import java.util.List;

public interface UserTypeDAO {

    List<UserType> findAll();
    Long insertUserType(UserType userType);
    UserType findUserTypeWithUserTypeId(Long userTypeId);
    UserType findUserTypeWithUserTypeName(String userTypeName);
    boolean updateUserType(UserType userType);

}
