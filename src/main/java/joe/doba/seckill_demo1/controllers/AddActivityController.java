package joe.doba.seckill_demo1.controllers;

import joe.doba.seckill_demo1.db.dao.ActivityDao;
import joe.doba.seckill_demo1.db.dao.CommodityDao;
import joe.doba.seckill_demo1.db.pojo.Activity;
import joe.doba.seckill_demo1.db.pojo.Commodity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Controller
public class AddActivityController {
    private ActivityDao activityDao;
    @Autowired
    public AddActivityController(ActivityDao activityDao) {
        this.activityDao = activityDao;
    }

    @Autowired
    private CommodityDao commodityDao;

    /**
     * 查询秒杀活动的列表
     *
     * @param resultMap
     * @return
     */
    @RequestMapping("/seckills")
    public String sucessTest(Map<String, Object> resultMap) {
        List<Activity> seckillActivities = activityDao.queryActivitysByStatus(1);
        resultMap.put("seckillActivities", seckillActivities);
        return "seckill_activity_list";
    }

    /**
     * 秒杀商品详情
     *
     * @param resultMap
     * @param seckillActivityId
     * @return
     */
    @RequestMapping("/seckill/detail/{seckillActivityId}")
    public String itemPage(Map<String, Object> resultMap, @PathVariable long seckillActivityId) {
        Activity activity = activityDao.queryActivityById(seckillActivityId);
        Commodity commodity = commodityDao.queryCommodityById(activity.getCommodityId());

        resultMap.put("activity", activity);
        resultMap.put("commodity", commodity);
        return "seckill_detail";
    }

//    @ResponseBody
//    @RequestMapping("/addSeckillActivityAction")
//    public String addActivityAction(
//            @RequestParam("name") String name,
//            @RequestParam("commodityId") long commodityId,
//            @RequestParam("seckillPrice") BigDecimal seckillPrice,
//            @RequestParam("oldPrice") BigDecimal oldPrice,
//            @RequestParam("seckillNumber") long seckillNumber,
//            @RequestParam("startTime") String startTime,
//            @RequestParam("endTime") String endTime
//    ) throws ParseException {
//        startTime = startTime.substring(0, 10) +  startTime.substring(11);
//        endTime = endTime.substring(0, 10) +  endTime.substring(11);
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-ddhh:mm");
//        Activity activity = new Activity();
//        activity.setName(name);
//        activity.setCommodityId(commodityId);
//        activity.setSeckillPrice(seckillPrice);
//        activity.setOldPrice(oldPrice);
//        activity.setTotalStock(seckillNumber);
//        activity.setAvailableStock(new Integer("" + seckillNumber));
//        activity.setLockStock(0L);
//        activity.setActivityStatus(1);
//        activity.setStartTime(format.parse(startTime));
//        activity.setEndTime(format.parse(endTime));
//        activityDao.insertActivity(activity);
//        return activity.toString();
//    }
//
//    @RequestMapping("/addSeckillActivity")
//    public String addSeckillActivity() {
//        return "add_activity";
//    }
}
