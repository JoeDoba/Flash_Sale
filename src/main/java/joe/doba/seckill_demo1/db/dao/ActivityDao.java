package joe.doba.seckill_demo1.db.dao;
import joe.doba.seckill_demo1.db.pojo.Activity;
import java.util.List;

public interface ActivityDao {

    public List<Activity> queryActivitysByStatus(int activityStatus);

    public void inertActivity(Activity activity);

    public Activity queryActivityById(long activityId);

    public void updateActivity(Activity activity);
}
