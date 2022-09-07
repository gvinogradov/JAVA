public class Account {

    private final boolean blocked;
    private final long money;
    private final String accNumber;

   public Account(long money, String accNumber, boolean blocked) {
        this.blocked = blocked;
        this.money = money;
        this.accNumber = accNumber;
    }

    public Account(long money, String accNumber) {
        this(money, accNumber, false);
    }

    public Account block() {
        return new Account(money, accNumber, true);
    }

    public boolean isBlocked() {
        return blocked;
    }

    public long getMoney() {
        return money;
    }

    public Account setMoney(long money) {
        return new Account(money, accNumber);
    }

    public String getAccNumber() {
        return accNumber;
    }

    public Account setAccNumber(String accNumber) {
        return new Account(money, accNumber, blocked);
    }

    @Override
    public String toString() {
        return accNumber + " : " + money + (blocked ? " - blocked" : "");
    }
}
