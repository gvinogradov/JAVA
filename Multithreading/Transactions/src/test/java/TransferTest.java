import junit.framework.TestCase;

import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class TransferTest extends TestCase {
    private final int ACCOUNT_COUNT = 10_0;
    Map<String, Account> accounts = new ConcurrentHashMap<>();
    Bank bank;

    public String generateKey() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public Account generateAccount() {
        long amount = (long) (Math.random() * 60_000 + 1000);
        return new Account(amount, generateKey());
    }


    @Override
    public void setUp() throws Exception {
        Stream.generate(() -> generateAccount())
                .limit(ACCOUNT_COUNT)
                .forEach(acc -> {
            accounts.put(acc.getAccNumber(), acc);
        });

        bank = new Bank(accounts);

        accounts.values().stream().forEach(a -> {
            System.out.println(a.getAccNumber() + " - " + a.getMoney());
        });

    }

    public void testGeneratedMap() {
        int actual =  ACCOUNT_COUNT;
        int expected = accounts.size();
    }
}
