import jakarta.persistence.Column;
import lombok.Getter;
import java.io.Serializable;

@Getter
public class SubscriptionKey implements Serializable {

    @Column(name = "student_id")
    private int studentId;

    @Column(name = "course_id")
    private int courseId;

    public SubscriptionKey() {
    }

    public SubscriptionKey(int studentId, int courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object obj) {
        SubscriptionKey subKey = (SubscriptionKey) obj;
        return studentId == subKey.getStudentId() &&
                courseId == subKey.getCourseId();
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(studentId + courseId);
    }
}
