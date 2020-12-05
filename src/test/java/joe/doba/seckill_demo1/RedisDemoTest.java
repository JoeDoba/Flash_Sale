package joe.doba.seckill_demo1;

import joe.doba.seckill_demo1.service.RedisOverSellService;
import joe.doba.seckill_demo1.util.Redis;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;

@SpringBootTest
@Slf4j
public class RedisDemoTest {

    @Autowired
    private Redis redisService;
    @Autowired
    private RedisOverSellService redisOverSellService;

    @Test
    public void setTest() {
        redisService.setValue("stock:12", 10L);
    }

    @Test
    public void getTest() {
        String stock = redisService.getValue("stock:12");
        System.out.println(stock);
    }
    @Test
    public void stockDeductValidatorTest(){
        boolean result =  redisService.stockDeducValidator("stock:19");
        System.out.println("result: " + result);
        String stock =  redisService.getValue("stock:19");
        System.out.println("stock: " + stock);
    }

    @Test
    public void pushInformToRedisTest() {
        redisOverSellService.pushInfoToRedis(19L);
    }

    @Test
    public  void getInfoFromRedisTest() {
        String activityInfo = redisService.getValue("activityId:" + 19);
        log.info(activityInfo);
    }
}
