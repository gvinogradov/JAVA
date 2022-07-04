public abstract class Client {

    public abstract String getName();
    protected double amount;
    protected double putComission;
    protected double takeComission;

    public double getAmount() {
        return amount;
    }

    public void put(double amount) {
        if (amount < 0.0) {
            return;
        }
        this.amount += amount;
    }

    public void take(double amount) {
        if (amount <= 0.0 || this.amount < amount) {
            return;
        }
        this.amount -= amount;
    }

    public void getConditions() {
        System.out.printf("Информация о счёте: \n " +
                "- комиссия на пополнение %.1f%%\n " +
                "- комиссия на снятие %.1f%%\n " +
                "- баланс %.2f \n", putComission * 100, takeComission * 100, amount);
    }

}
