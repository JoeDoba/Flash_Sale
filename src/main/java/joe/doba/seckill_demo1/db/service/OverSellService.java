package joe.doba.seckill_demo1.db.service;

import joe.doba.seckill_demo1.db.dao.ActivityDao;
import joe.doba.seckill_demo1.db.po.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OverSellService {

    private ActivityDao activityDao;
    @Autowired
    public OverSellService(ActivityDao activityDao) {
        this.activityDao = activityDao;
    }

    public String processSecKill(Long activityID) {
        Activity activity = activityDao.queryActivityById(activityID);
        Integer availableStock = activity.getAvailableStock();
        String result;
        if (availableStock > 0) {
            result = "Congrats!! You have successfully purchased!!";
            System.out.println(result);
            availableStock--;
            activity.setAvailableStock(availableStock);
            activityDao.updateActivity(activity);
        } else {
            result = "Sorry, the item was sold out!";
            System.out.println(result);
        }
        return result;
    }
}
