package joe.doba.seckill_demo1;

import joe.doba.seckill_demo1.util.Redis;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;

@SpringBootTest
public class RedisDemoTest {

    @Resource
    private Redis redisService;

    @Test
    public void setTest() {
        redisService.setValue("stock:12", 10L);
    }

    @Test
    public void getTest() {
        String age = redisService.getValue("stock:12");
        System.out.println(age);
        System.out.println(redisService == null);
    }
    @Test
    public void stockDeductValidatorTest(){
        boolean result =  redisService.stockDeducValidator("stock:19");
        System.out.println("result: " + result);
        String stock =  redisService.getValue("stock:19");
        System.out.println("stock: " + stock);
    }
}
