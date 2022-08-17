import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {

            Course course = session.get(Course.class, 1);
            System.out.println(course.getType());
            System.out.printf("Course ID: %d \nCourse name: %s\nCourse teacher: %s\n\n", course.getId(), course.getName(), course.getTeacher().getName());

            System.out.print("\nCourse students, with subscriptions:");
            List<Student> students = course.getStudents();
            students.forEach(s -> {
                List<Subscription> subscriptions = s.getSubscriptions();
                System.out.printf("\nStudent: %s\n", s.getName());
                subscriptions.forEach(subscription -> {
                        Date subscriptionDate = subscription.getSubscriptionDate();
                        Course currentCourse = subscription.getCourse();
                    System.out.printf("Course name: %s, subscription date: %s\n",currentCourse.getName(), subscriptionDate);
                });
             });

            Date date = new SimpleDateFormat("yyyy-MM-dd ").parse("2018-01-02 00:00:00");
            PurchaseList purchaseList = session.get(PurchaseList.class,
                    new PurchaseListKey(189600, date));

            System.out.printf("\nGet PurchaseList record:\n%s\t%s\t%s\t%s\n", purchaseList.getStudentName(),
                    purchaseList.getCourseName(),
                    purchaseList.getPrice(),
                    purchaseList.getSubscriptionDate());
        } catch (Exception e) {
            e.printStackTrace();
        }
        sessionFactory.close();
    }
}
