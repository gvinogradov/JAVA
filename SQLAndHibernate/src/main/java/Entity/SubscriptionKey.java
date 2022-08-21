package Entity;

import java.io.Serializable;
import java.util.Objects;

public class SubscriptionKey implements Serializable {
    private int studentId;
    private int courseId;

    public SubscriptionKey() {
    }

    public SubscriptionKey(int studentId, int courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SubscriptionKey subKey = (SubscriptionKey) obj;
        return studentId == subKey.studentId &&
                courseId == subKey.courseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, studentId);
    }
}
