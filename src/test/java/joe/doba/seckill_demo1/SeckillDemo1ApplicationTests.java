package joe.doba.seckill_demo1;
import joe.doba.seckill_demo1.db.mappers.ActivityMapper;
import joe.doba.seckill_demo1.db.pojo.Activity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;
import java.math.BigDecimal;

@SpringBootTest
class SeckillDemo1ApplicationTests {

    @Resource
    private ActivityMapper activityMapper;

    @Test
    public void SeckillActivityTest() {
        Activity activity = new Activity();
        activity.setName("test1234");
        activity.setCommodityId(1111L);
        activity.setTotalStock(10000L);
        activity.setOldPrice(new BigDecimal(200));
        activity.setSeckillPrice(new BigDecimal(88));
        activity.setActivityStatus(10);
        activity.setAvailableStock(100);
        activity.setLockStock(0L);
        activityMapper.insert(activity);
        System.out.println("======>" + activityMapper.selectByPrimaryKey(1L));
    }

}
