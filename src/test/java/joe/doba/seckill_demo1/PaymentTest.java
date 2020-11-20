package joe.doba.seckill_demo1;

import joe.doba.seckill_demo1.service.RedisOverSellService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class PaymentTest {
    @Resource
    private RedisOverSellService redisOverSellService;

    @Test
    public void afterPay() {
        redisOverSellService.payOrder("527039271157239808");
    }
}
