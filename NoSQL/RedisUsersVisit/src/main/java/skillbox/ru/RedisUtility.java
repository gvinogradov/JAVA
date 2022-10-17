package skillbox.ru;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;

public class RedisUtility {
    private static String REDIS_ADDRESS = "redis://192.168.5.89:6379";
    private static RedissonClient redissonClient;

    public RedisUtility() {
    }

    public static synchronized RedissonClient getRedissonClient() throws RedisConnectionException {
        if (redissonClient == null) {
            Config config = new Config();
            config.useSingleServer()
                    .setAddress(REDIS_ADDRESS);
            redissonClient = Redisson.create(config);
        }
        return redissonClient;
    }

    public static void shutdown() {
        redissonClient.shutdown();
    }
}
