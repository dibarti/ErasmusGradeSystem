package com.erasmus.grades.model;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserToCourseId implements Serializable {

    private User user;
    private Course course;

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }


//    @Column(name = "user_id")
//    private Long userId;
//    @Column(name = "class_id")
//    private Long courseId;
//
//    public UserToCourseId() {}
//
//    public UserToCourseId(
//            Long userId,
//            Long courseId) {
//        this.userId = userId;
//        this.courseId = courseId;
//    }
//
//    public Long getUserId() {
//        return userId;
//    }
//
//    public Long getCourseId() {
//        return courseId;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        UserToCourseId that = (UserToCourseId) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(course, that.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, course);
    }
}
