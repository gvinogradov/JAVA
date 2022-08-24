import entity.Course;
import entity.LinkedPurchaseList;
import entity.PurchaseList;
import entity.Student;
import service.CourseService;
import service.LinkedPurchaseListService;
import service.PurchaseListService;
import service.StudentService;
import utils.HibernateUtility;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            CourseService courseService = new CourseService();
            StudentService studentService = new StudentService();
            PurchaseListService purchaseListService = new PurchaseListService();
            LinkedPurchaseListService linkedPurchaseListService = new LinkedPurchaseListService();

            List<Course> courses = courseService.getAll();
            List<Student> students = studentService.getAll();
            List<PurchaseList> purchaseLists = purchaseListService.getAll();

            List<LinkedPurchaseList> linkedPurchaseLists = new ArrayList<>();

            for (PurchaseList purchaseList: purchaseLists) {
                Integer courseId = courseService.getIdByName(courses, purchaseList.getCourseName());
                Integer studentId = studentService.getIdByName(students, purchaseList.getStudentName());
                if (courseId == null || studentId == null) {
                    continue;
                }
                linkedPurchaseLists.add(new LinkedPurchaseList(studentId, courseId));
            }

            linkedPurchaseListService.addAll(linkedPurchaseLists);

        } catch (Exception e) {
            e.printStackTrace();
        }
        HibernateUtility.getSessionFactory().close();
    }
}
