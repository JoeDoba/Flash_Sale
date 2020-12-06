package joe.doba.seckill_demo1.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Collections;

@Service
public class Redis {
    private final JedisPool jedisPool;

    @Autowired
    public Redis(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public void setValue(String key, Long value) {
        Jedis jedisClient = jedisPool.getResource();
        jedisClient.set(key, value.toString());
    }

    public void setValue(String key, String value) {
        Jedis jedisClient = jedisPool.getResource();
        jedisClient.set(key, value);
    }

    public String getValue(String key) {
        Jedis jedisClient = jedisPool.getResource();
        return jedisClient.get(key);
    }

    public boolean stockDeducValidator(String key) {
        try {
            String script = "if (redis.call('exists',KEYS[1]) == 1) then\n" +
                    "                 local stock = tonumber(redis.call('get', KEYS[1]));\n" +
                    "                 if( stock <=0 ) then\n" +
                    "                    return -1\n" +
                    "                 end;\n" +
                    "                 redis.call('decr',KEYS[1]);\n" +
                    "                 return stock - 1;\n" +
                    "             end;\n" +
                    "             return -1;";
            Jedis jedisClient = jedisPool.getResource();
            Long stock = (Long) jedisClient.eval(script, Collections.singletonList(key), Collections.emptyList());
            if (stock < 0) {
                System.out.println("Not enough inventory");
                return false;
            } else {
                System.out.println("Purchase successfully!!");
            }
            return true;
        } catch (Throwable throwable) {
            System.out.println("Inventory deduction failed!!" + throwable.toString());
            return false;
        }
    }

    public boolean getDistributedLock(String lockKey, String requestId, int expireTime) {
        Jedis jedisClient = jedisPool.getResource();
        // NX: Set if not exist
        // If key doesn't exist -> set. Otherwise, just skipping.
        // PX: Key lock will expire after a certain amount of time depends on value 'expireTime'
        String result = jedisClient.set(lockKey, requestId, "NX", "PX", expireTime);
        return "OK".equals(result);
    }

    public boolean releaseDistributedLock(String lockKey, String requestId) {
        Jedis jedisClient = jedisPool.getResource();
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Long result = (Long) jedisClient.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
        if (result == 1L) {
            return true;
        }
        return false;
    }
}
