package joe.doba.seckill_demo1.db.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Collections;

@Service
public class RedisService {
    private JedisPool jedisPool;
    private Object throwable;

    @Autowired
    public RedisService(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public void setValue(String key, Long value) {
        Jedis jedisClient = jedisPool.getResource();
        jedisClient.set(key, value.toString());
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
}
