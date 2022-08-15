import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "Subscriptions")
@Getter
public class Subscription {
    @EmbeddedId
    @Setter
    private SubscriptionKey id;

    @Column(name = "student_id", insertable = false, updatable = false)
    private int studentId;

    @Column(name = "course_id", insertable = false, updatable = false)
    private int courseId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Student student;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    private Course course;

    @Column(name = "subscription_date")
    @Setter
    private Date subscriptionDate;

}
