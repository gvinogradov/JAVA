import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Company company1 = new Company();

        for (int i = 0; i < 180; i++) {
            company1.hire(new Operator(randomSalary(50_000, 120_000)));
        }

        ArrayList<Employee> staffToHire = new ArrayList<>();
        for (int i = 0; i < 80; i++) {
            staffToHire.add(new Manager(randomSalary(80_000, 150_000)));
        }
        company1.hireAll(staffToHire);

        for (int i = 0; i < 10; i++) {
            company1.hire(new TopManager(randomSalary(100_000, 200_000)));
        }

        System.out.println("Список самых высоких зарплат в компании:");
        printSalaryList(company1.getTopSalaryStaff(15));

        System.out.println("Список самых низких зарплат в компании:");
        printSalaryList(company1.getLowestSalaryStaff(30));


        System.out.println("Увольняем 50 % сотрудников:");
        ArrayList<Employee> staff = company1.getStaff();
        for (int i = 0; i < staff.size() / 2; i++) {
            company1.fire(staff.get(i));
        }

        System.out.println("Список самых высоких зарплат в компании:");
        printSalaryList(company1.getTopSalaryStaff(15));

        System.out.println("Список самых низких зарплат в компании:");
        printSalaryList(company1.getLowestSalaryStaff(30));
    }

    public static void printSalaryList(List<Employee> employees) {
        for (Employee employee : employees) {
            System.out.printf("%.0f руб\n", employee.getMonthSalary());
        }
    }

    public static double randomSalary(double min, double max) {
        return Math.random() * (max - min) + min;
    }
}
