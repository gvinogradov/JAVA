import java.util.*;
public class Company {
    private ArrayList<Employee> employeeList;
    private double income;

    public Company() {
        employeeList = new ArrayList<>();
        this.income = 0.0;
    }

    public double getIncome() {
        return income;
    }

    public void hire(Employee employee) {
        if (employee instanceof Manager) {
            double income = Math.random() * (140_000 - 115_000) + 115_000;
            this.income += income;
            ((Manager) employee).addPremium(this);
        }
        if (employee instanceof TopManager) {
            ((TopManager) employee).addPremium(this);
        }
        employeeList.add(employee);
    }

    public void hireAll(Collection<Employee> employees) {
        for (Employee employee : employees) {
            hire(employee);
        }
    }

    public void fire(Employee employee) {
        if (employeeList.contains(employee)) {
            employeeList.remove(employee);
        }
    }

    public ArrayList<Employee> getTopSalaryStaff(int count) {
        if (count <= 0 || count > employeeList.size()) {
            return new ArrayList<>();
        }
        List<Employee> topSalaries = (ArrayList<Employee>) employeeList.clone();
        Comparator<Employee> salaryCompare = Comparator.comparing(Employee::getMonthSalary);
        topSalaries.sort(salaryCompare.reversed());
        return new ArrayList<>(topSalaries.subList(0, count));
    }

    public ArrayList<Employee> getLowestSalaryStaff(int count) {
        if (count <= 0 || count > employeeList.size()) {
            return new ArrayList<>();
        }
        List<Employee> topSalaries = (ArrayList<Employee>) employeeList.clone();
        Comparator<Employee> salaryCompare = Comparator.comparing(Employee::getMonthSalary);
        topSalaries.sort(salaryCompare);
        return new ArrayList<>(topSalaries.subList(0, count));
    }

    public ArrayList<Employee> getStaff() {
        return (ArrayList<Employee>) employeeList.clone();
    }

}
