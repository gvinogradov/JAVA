public class Manager implements Employee {
    private double salary;
    private static final double PREMIUM = 0.05;  // 5%

    public Manager(double income) {
        salary = income;
    }

    @Override
    public double getMonthSalary() {
        return salary;
    }

    public void addPremium(Company company) {
        salary += PREMIUM * company.getIncome();
    }

}
