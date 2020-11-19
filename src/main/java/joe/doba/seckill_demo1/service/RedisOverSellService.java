package joe.doba.seckill_demo1.service;

import joe.doba.seckill_demo1.util.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedisOverSellService {
    private RedisService redisService;

    @Autowired
    public RedisOverSellService(RedisService redisService) {
        this.redisService = redisService;
    }

    public boolean stockDeductValidator(Long activityId) {
        String key = "stock:" + activityId;
        return redisService.stockDeducValidator(key);
    }
}
