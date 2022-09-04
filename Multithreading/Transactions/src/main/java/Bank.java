import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Bank {

    private final static int MIN_FRAUD_LIMIT = 50_000;
    private Map<String, Account> accounts = new ConcurrentHashMap<>();
    private final Random random = new Random();

    public Bank(Map<String, Account> accounts) {
        this.accounts = accounts;
    }

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
        throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами. Если сумма транзакции > 50000,
     * то после совершения транзакции, она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка счетов (как – на ваше
     * усмотрение)
     */
    public void transfer(String fromAccountNum, String toAccountNum, long amount) {

        Account srcAccont = accounts.get(fromAccountNum);
        Account dstAccont = accounts.get(toAccountNum);

        synchronized (srcAccont) {
            if (srcAccont.isBlocked() || srcAccont.getMoney() < amount || amount <= 0) {
                return;
            }
            synchronized (dstAccont) {
                if (dstAccont.isBlocked()) {
                    return;
                }
                srcAccont = srcAccont.setMoney(srcAccont.getMoney() - amount);
                dstAccont = dstAccont.setMoney(dstAccont.getMoney() + amount);

                if (amount > MIN_FRAUD_LIMIT) {
                    try {
                        if (isFraud(fromAccountNum, toAccountNum, amount)) {
                            srcAccont = srcAccont.block();
                            dstAccont = dstAccont.block();
                            return;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum) {
        synchronized (accounts.get(accountNum)) {
            return accounts.get(accountNum).getMoney();
        }
    }

    public long getSumAllAccounts() {
        synchronized (accounts) {
            long sum = accounts.values().stream().mapToLong(i -> i.getMoney()).sum();
            return sum;
        }
    }
}
