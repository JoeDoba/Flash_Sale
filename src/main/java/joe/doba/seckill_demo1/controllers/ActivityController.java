package joe.doba.seckill_demo1.controllers;

import joe.doba.seckill_demo1.db.dao.ActivityDao;
import joe.doba.seckill_demo1.db.dao.CommodityDao;
import joe.doba.seckill_demo1.db.pojo.Activity;
import joe.doba.seckill_demo1.db.pojo.Commodity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
public class ActivityController {
    private ActivityDao activityDao;
    private CommodityDao commodityDao;
    @Autowired
    public ActivityController(ActivityDao activityDao, CommodityDao commodityDao) {
        this.activityDao = activityDao;
        this.commodityDao = commodityDao;
    }

    /**
     * Activity Main Page
     **/
    @RequestMapping("/seckills")
    public String sucessTest(Map<String, Object> resultMap) {
        List<Activity> seckillActivities = activityDao.queryActivitysByStatus(1);
        resultMap.put("seckillActivities", seckillActivities);
        return "seckill_activity_list";
    }

    /**
     * Activity and commodity detail
     **/
    @RequestMapping("/seckill/detail/{seckillActivityId}")
    public String itemPage(Map<String, Object> resultMap, @PathVariable long seckillActivityId) {
        Activity activity = activityDao.queryActivityById(seckillActivityId);
        Commodity commodity = commodityDao.queryCommodityById(activity.getCommodityId());
        resultMap.put("activity", activity);
        resultMap.put("commodity", commodity);
        return "seckill_detail";
    }
}
