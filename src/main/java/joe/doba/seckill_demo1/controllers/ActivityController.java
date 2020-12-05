package joe.doba.seckill_demo1.controllers;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSON;
import joe.doba.seckill_demo1.db.dao.ActivityDao;
import joe.doba.seckill_demo1.db.dao.CommodityDao;
import joe.doba.seckill_demo1.db.pojo.Activity;
import joe.doba.seckill_demo1.db.pojo.Commodity;
import joe.doba.seckill_demo1.util.Redis;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class ActivityController {
    private ActivityDao activityDao;
    private CommodityDao commodityDao;
    private Redis redisService;

    @Autowired
    public ActivityController(ActivityDao activityDao, CommodityDao commodityDao, Redis redisService) {
        this.activityDao = activityDao;
        this.commodityDao = commodityDao;
        this.redisService = redisService;
    }

    /**
     * Activity Main Page
     **/
    @RequestMapping("/seckills")
    public String sucessTest(Map<String, Object> resultMap) {
        try (Entry entry = SphU.entry("activityPage")) {
            List<Activity> seckillActivities = activityDao.queryActivitysByStatus(1);
            resultMap.put("seckillActivities", seckillActivities);
            return "seckill_activity_list";
        } catch (BlockException blockException) {
            log.info(blockException.toString());
            return "wait";
        }
    }

    /**
     * Activity and commodity detail
     **/

    @RequestMapping("/seckill/detail/{activityId}")
    public String itemPage(Map<String, Object> resultMap, @PathVariable long activityId) {

        Activity activity;
        Commodity commodity;

        /**
         *  Check if redis cached activity and commodity information before query SQL database.
         **/

        String activityInfo = redisService.getValue("activityId:" + activityId);
        if (StringUtils.isNotEmpty(activityInfo)) {
            log.info("Redis cache found activity information: " + activityInfo);
            activity = JSON.parseObject(activityInfo, Activity.class);
        } else {
            activity = activityDao.queryActivityById(activityId);
        }

        Long commodityId = activity.getCommodityId();
        String commodityInfo = redisService.getValue("commodityId:" + commodityId);
        if (StringUtils.isNotEmpty(commodityInfo)) {
            log.info("Redis cache found commodity information: " + commodityInfo);
            commodity = JSON.parseObject(commodityInfo, Commodity.class);
        } else {
            commodity = commodityDao.queryCommodityById(commodityId);
        }

        resultMap.put("activity", activity);
        resultMap.put("commodity", commodity);
        return "seckill_detail";
    }
}
