package joe.doba.seckill_demo1.db.dao;

import joe.doba.seckill_demo1.db.mappers.ActivityMapper;
import joe.doba.seckill_demo1.db.po.Activity;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.util.List;

@Repository
public class ActivityDaoImpl implements ActivityDao {

    @Resource
    private ActivityMapper activityMapper;

    @Override
    public List<Activity> queryActivitysByStatus(int activityStatus) {
        return activityMapper.queryActivitysByStatus(activityStatus);
    }

    @Override
    public void inertActivity(Activity activity) {
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
}
