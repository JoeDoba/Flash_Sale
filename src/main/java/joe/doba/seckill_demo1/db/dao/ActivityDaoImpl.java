package joe.doba.seckill_demo1.db.dao;

import joe.doba.seckill_demo1.db.mappers.ActivityMapper;
import joe.doba.seckill_demo1.db.pojo.Activity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.util.List;
@Slf4j
@Repository
public class ActivityDaoImpl implements ActivityDao {

    @Resource
    private ActivityMapper activityMapper;

    @Override
    public List<Activity> queryActivitysByStatus(int activityStatus) {
        return activityMapper.queryActivitysByStatus(activityStatus);
    }

    @Override
    public void insertActivity(Activity activity) {
        activityMapper.insert(activity);
    }

    @Override
    public Activity queryActivityById(long activityId) {
        return activityMapper.selectByPrimaryKey(activityId);
    }

    @Override
    public void updateActivity(Activity activity) {
        activityMapper.updateByPrimaryKey(activity);
    }

    @Override
    public boolean deductInventory(long activityId) {
        int result = activityMapper.deductInventory(activityId);
        if (result < 1) {
            log.info("Inventory deduction fail");
            return false;
        }
        return true;
    }

    @Override
    public boolean lockInventory(long activityId) {
        int result = activityMapper.lockInventory(activityId);
        if (result < 1) {
            log.info("Inventory lock fail");
            return false;
        }
        return true;
    }
}
