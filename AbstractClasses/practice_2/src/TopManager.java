public class TopManager implements Employee {

    private double salary;
    private static final double INCOME_FOR_PREMIUM = 10_000_000.0;
    private static final double PREMIUM = 1.5;  // 150%

    public TopManager(double salary) {
        this.salary = salary;
    }

    @Override
    public double getMonthSalary() {
        return salary;
    }

    public void addPremium(Company company) {
        if (company.getIncome() >= INCOME_FOR_PREMIUM) {
            salary *= (1 + PREMIUM);
        }
    }
}
