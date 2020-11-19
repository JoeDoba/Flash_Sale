package joe.doba.seckill_demo1;

import joe.doba.seckill_demo1.util.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
@SpringBootTest
public class RedisDemoTest {

    @Resource
    private RedisService redisService;

    @Test
    public void setTest() {
        redisService.setValue("stock:12", 10L);
    }
    @Test
    public void getTest() {
        String age = redisService.getValue("stock:12");
        System.out.println(age);
    }
    @Test
    public void stockDeductValidatorTest(){
        boolean result =  redisService.stockDeducValidator("stock:12");
        System.out.println("result: " + result);
        String stock =  redisService.getValue("stock:12");
        System.out.println("stock: " + stock);
    }

//    @Resource
//    private RedisOverSellControllorer redisOverSellControllorer;

//    @Test
//    public void resultAnalysis() {
//        System.out.println("Successful purchase: " + redisOverSellControllorer.successCnt);
//        System.out.println("Fail purchase: " + redisOverSellControllorer.failCnt);
//    }
}
