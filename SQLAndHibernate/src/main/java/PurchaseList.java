import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "PurchaseList")
@Getter
public class PurchaseList {

    @EmbeddedId
    @Setter
    private PurchaseListKey id;

    @Column(name = "student_name", insertable = false, updatable = false)
    private String studentName;

    @Column(name = "course_name", insertable = false, updatable = false)
    private String courseName;

    @Column(insertable = false, updatable = false)
    private int price;

    @Column(name = "subscription_date", insertable = false, updatable = false)
    private Date subscriptionDate;
}
