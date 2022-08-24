package entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table( name = "Subscriptions", indexes = @Index(
        columnList = "student_id, course_id",
        name = "unq")
)
public class Subscription {
    @EmbeddedId
    private SubscriptionKey subscriptionKey;

    @Column(name = "student_id", insertable = false, updatable = false)
    private int studentId;

    @Column(name = "course_id", insertable = false, updatable = false)
    private int courseId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    private Course course;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    public Subscription() {
    }

    public SubscriptionKey getSubscriptionKey() {
        return subscriptionKey;
    }

    public void setSubscriptionKey(SubscriptionKey subscriptionKey) {
        this.subscriptionKey = subscriptionKey;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }
}
