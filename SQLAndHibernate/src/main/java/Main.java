import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            for (int i = 1; i <= 45; i++) {
                Course course = session.get(Course.class, i);
                System.out.println(course.getId() + " - " + course.getName() + " - " + course.getStudentsCount());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        sessionFactory.close();
    }
}
