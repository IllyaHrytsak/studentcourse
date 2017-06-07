package ua.training.entity;


import java.io.Serializable;

public class UserStore implements Serializable {

    private Integer mark;
    private Course relationshipCourse;
    private User relationshipUser;

    public UserStore() {

    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Course getRelationshipCourse() {
        return relationshipCourse;
    }

    public void setRelationshipCourse(Course relationshipCourse) {
        this.relationshipCourse = relationshipCourse;
    }

    public User getRelationshipUser() {
        return relationshipUser;
    }

    public void setRelationshipUser(User relationshipUser) {
        this.relationshipUser = relationshipUser;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof UserStore)) return false;

        UserStore userStore = (UserStore) obj;

        if (getMark() != null ? !getMark().equals(userStore.getMark()) : userStore.getMark() != null) return false;
        if (!getRelationshipCourse().equals(userStore.getRelationshipCourse())) return false;
        return getRelationshipUser().equals(userStore.getRelationshipUser());
    }

    @Override
    public int hashCode() {
        int result = getMark() != null ? getMark().hashCode() : 0;
        result = 31 * result + getRelationshipCourse().hashCode();
        result = 31 * result + getRelationshipUser().hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Course name: ")
                .append(relationshipCourse.getNameCourse())
                .append(", student login: ")
                .append(relationshipUser.getLogin())
                .append(", mark: ")
                .append((mark != null) ? mark : "");
        return stringBuilder.toString();
    }
}
