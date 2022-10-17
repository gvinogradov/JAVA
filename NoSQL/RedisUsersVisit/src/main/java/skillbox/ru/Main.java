package skillbox.ru;

import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;

import java.util.Date;
import java.util.Random;

public class Main {
    private final static int SLEEP = 100;
    private final static int USERS = 20;
    private final static String KEY = "ONLINE_USERS";

    private static RScoredSortedSet<String> onlineUsers;

    public static void main(String[] args) {
        try {
            RedissonClient redissonClient = RedisUtility.getRedissonClient();
            onlineUsers = redissonClient.getScoredSortedSet(KEY);
            initUsers();

            int iterate = 1;
            String currentUser;
            while (true) {
                Thread.sleep(SLEEP);
                if (iterate++ % 10 == 0) {
                    currentUser = getPaymentUser();
                    System.out.printf("> Пользователь %s оплатил платную услугу\n", currentUser);
                } else {
                    currentUser = getNextUser();
                }
                System.out.println("— На главной странице показываем пользователя " + currentUser);
            }
        } catch (RedisConnectionException | InterruptedException ex) {
            ex.printStackTrace();
        }
        RedisUtility.shutdown();
    }

    private static Long getTs() {
        return new Date().getTime();
    }

    private static void logPageVisit(int userId) {
        onlineUsers.add(getTs(), String.valueOf(userId));
    }

    private static void initUsers() throws InterruptedException {
        for (int i = 0; i < USERS; i++) {
            logPageVisit(i);
            Thread.sleep(SLEEP);
        }
    }

    private static String getPaymentUser() {
        int paymentUser = new Random().nextInt(USERS);
        logPageVisit(paymentUser);
        return String.valueOf(paymentUser);
    }

    private static String getNextUser() {
        String nextUser = onlineUsers.pollFirst();
        int usersNumber = Integer.parseInt(nextUser);
        logPageVisit(usersNumber);
        return nextUser;
    }
}