package com.erasmus.grades.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_to_class")
@AssociationOverrides({
        @AssociationOverride(name = "pk.user",
                joinColumns = @JoinColumn(name = "user_id")),
        @AssociationOverride(name = "pk.course",
                joinColumns = @JoinColumn(name = "class_id"))})
public class UserToCourse implements Serializable {

    @EmbeddedId
    private UserToCourseId pk;
    @Column(name = "grade")
    private int grade;

    public UserToCourse() {
    }

    public UserToCourseId getPk() {
        return pk;
    }

    public void setPk(UserToCourseId pk) {
        this.pk = pk;
    }

    @Transient
    public User getUser() {
        return getPk().getUser();
    }

    public void setUser(User user) {
        getPk().setUser(user);
    }

    @Transient
    public Course getCourse() {
        return getPk().getCourse();
    }

    public void setCourse(Course course) {
        getPk().setCourse(course);
    }


    //    @EmbeddedId
//    private UserToCourseId id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId("userId")
//    private User user;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId("courseId")
//    private Course course;
//
//    @Column(name = "grade")
//    private int grade;
//
//    public UserToCourse() {}
//
//    public UserToCourse(User user, Course course) {
//        this.user = user;
//        this.course = course;
//        this.id = new UserToCourseId(user.getUserId(), course.getCourseId());
//    }
//
//    //Getters and setters omitted for brevity
//
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public Course getCourse() {
//        return course;
//    }
//
//    public void setCourse(Course course) {
//        this.course = course;
//    }
//
    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//
//        if (o == null || getClass() != o.getClass())
//            return false;
//
//        UserToCourse that = (UserToCourse) o;
//        return Objects.equals(, that.user) &&
//                Objects.equals(course, that.course);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(user, course);
//    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        UserToCourse that = (UserToCourse) o;

        return getPk() != null ? getPk().equals(that.getPk()) : that.getPk() == null;
    }

    public int hashCode() {
        return (getPk() != null ? getPk().hashCode() : 0);
    }
}
