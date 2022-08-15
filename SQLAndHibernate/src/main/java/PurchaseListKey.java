import jakarta.persistence.Column;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

@Getter
public class PurchaseListKey implements Serializable {
    @Column(name = "student_name")
    private String studentName;

    @Column(name = "course_name")
    private String courseName;

    private int price;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    public PurchaseListKey() {
    }

    public PurchaseListKey(String studentName, String courseName, int price, Date subscriptionDate) {
        this.studentName = studentName;
        this.courseName = courseName;
        this.price = price;
        this.subscriptionDate = subscriptionDate;
    }

    @Override
    public int hashCode() {
        return studentName.hashCode() + courseName.hashCode() +
                subscriptionDate.hashCode() + Integer.hashCode(price);
    }

    @Override
    public boolean equals(Object obj) {
        PurchaseList pList = (PurchaseList) obj;
        return studentName.equals(pList.getStudentName()) &&
                courseName.equals(pList.getCourseName()) &&
                subscriptionDate.equals(pList.getSubscriptionDate()) &&
                price == pList.getPrice();
    }
}
