import junit.framework.TestCase;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class TransferTest extends TestCase {
    private static final int CORES = Runtime.getRuntime().availableProcessors();
    private final int ACCOUNT_COUNT = 8;
    private final int TRANS_COUNT = 5000;
    private final boolean TRANS_NOT_COMMIT = false;
    Map<String, Account> accounts = new ConcurrentHashMap<>();
    Bank bank;

    List<String> AccountKeys = new ArrayList<>();
    Transaction[] transactions = new Transaction[TRANS_COUNT];


    public String generateKey() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public Account generateAccount() {
        long amount = (long) (Math.random() * 50_000 + 50_000);
        return new Account(amount, generateKey());
    }

    private void generateTrans() {
        Random rnd = new Random();
        for (int i = 0; i < TRANS_COUNT; i++) {
            int src = 0;
            int dst = 0;
            while (dst == src) {
                src = rnd.nextInt(ACCOUNT_COUNT);
                dst = rnd.nextInt(ACCOUNT_COUNT);
            }
            long srcMoney = accounts.get(AccountKeys.get(src)).getMoney();
            long amount = (i % 20 == 0) ? (long) (Math.random() * srcMoney) : (long) (Math.random() * 10_000);
            transactions[i] = new Transaction(AccountKeys.get(src),
                    AccountKeys.get(dst),
                    amount,
                    TRANS_NOT_COMMIT);
        }
    }

    private void generateAccounts() {
        Stream.generate(() -> generateAccount())
                .limit(ACCOUNT_COUNT)
                .forEach(acc -> {
                    accounts.put(acc.getAccNumber(), acc);
                    AccountKeys.add(acc.getAccNumber());
                });
    }


    @Override
    public void setUp() throws Exception {
        generateAccounts();
        generateTrans();
        bank = new Bank(accounts);
      //  accounts.values().stream().forEach(System.out::println);
    }

    public void testCheckSumAfterTrans() throws InterruptedException {

        long startSumAllAccount = bank.getSumAllAccounts();

        Thread[] threads = new Thread[CORES];
        for (int i = 0; i < CORES; i++) {
            int length = i < CORES - 1 ? transactions.length / CORES :
                    transactions.length / CORES + transactions.length % CORES;
            Transaction[] oneThreadTrans = new Transaction[length];
            System.arraycopy(transactions, i * (transactions.length / CORES), oneThreadTrans, 0, length);
            TransactionService oneThreadService = new TransactionService(bank, oneThreadTrans, i + 1, CORES);
            threads[i] = new Thread(oneThreadService);
        }

        for (Thread th : threads) {
            th.start();
        }
        for (Thread th : threads) {
            th.join();
        }
        long actual = startSumAllAccount;
        long expected = bank.getSumAllAccounts();
    }

    public void testGeneratedMap() {
        int actual =  ACCOUNT_COUNT;
        int expected = accounts.size();
    }
}
