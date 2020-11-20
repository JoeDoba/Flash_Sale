package joe.doba.seckill_demo1.controllers;

import joe.doba.seckill_demo1.db.dao.ActivityDao;
import joe.doba.seckill_demo1.db.pojo.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
public class AddActivityController {

    private ActivityDao activityDao;
    @Autowired
    public AddActivityController(ActivityDao activityDao) {
        this.activityDao = activityDao;
    }

    @ResponseBody
    @RequestMapping("/addSeckillActivityAction")
    public String addActivityAction(
            @RequestParam("name") String name,
            @RequestParam("commodityId") long commodityId,
            @RequestParam("seckillPrice") BigDecimal seckillPrice,
            @RequestParam("oldPrice") BigDecimal oldPrice,
            @RequestParam("seckillNumber") long seckillNumber,
            @RequestParam("startTime") String startTime,
            @RequestParam("endTime") String endTime
    ) throws ParseException {
        startTime = startTime.substring(0, 10) +  startTime.substring(11);
        endTime = endTime.substring(0, 10) +  endTime.substring(11);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-ddhh:mm");
        Activity activity = new Activity();
        activity.setName(name);
        activity.setCommodityId(commodityId);
        activity.setSeckillPrice(seckillPrice);
        activity.setOldPrice(oldPrice);
        activity.setTotalStock(seckillNumber);
        activity.setAvailableStock(new Integer("" + seckillNumber));
        activity.setLockStock(0L);
        activity.setActivityStatus(1);
        activity.setStartTime(format.parse(startTime));
        activity.setEndTime(format.parse(endTime));
        activityDao.insertActivity(activity);
        return activity.toString();
    }

    @RequestMapping("/addSeckillActivity")
    public String addSeckillActivity() {
        return "add_activity";
    }
}
