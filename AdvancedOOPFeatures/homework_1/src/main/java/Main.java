import java.util.*;

public class Main {

    public static final String STAFF_TXT = "data/staff.txt";

    public static void main(String[] args) {
        List<Employee> staff = Employee.loadStaffFromFile(STAFF_TXT);

        staff.stream().forEach(System.out::println);
        System.out.println("\n");
        sortBySalaryAndAlphabet(staff);
        staff.stream().forEach(System.out::println);

    }

    public static void sortBySalaryAndAlphabet(List<Employee> staff) {
        staff.sort((o1, o2) -> o1.getSalary().compareTo(o2.getSalary()) == 0 ?
                o1.getName().compareTo(o2.getName()) :
                o1.getSalary().compareTo(o2.getSalary()));
    }
}