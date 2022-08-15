import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Entity
@Table(name = "Courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    private int id;

    @Setter
    private String name;

    @Setter
    private int duration;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Subscriptions",
    joinColumns = {@JoinColumn(name = "course_id")},
    inverseJoinColumns = {@JoinColumn(name = "student_id")})
    List<Student> students;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum")
    @Setter
    private CourseType type;

    @Setter
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    private Teacher teacher;

    @Column(name = "students_count")
    @Setter
    private int studentsCount;

    @Setter
    private int price;

    @Column(name = "price_per_hour")
    @Setter
    private float pricePerHour;
}
