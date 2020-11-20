package joe.doba.seckill_demo1.db.dao;
import joe.doba.seckill_demo1.db.pojo.Activity;
import java.util.List;

public interface ActivityDao {

    public List<Activity> queryActivitysByStatus(int activityStatus);

    public void insertActivity(Activity activity);

    public Activity queryActivityById(long activityId);

    public void updateActivity(Activity activity);

    public boolean deductInventory(long activityId);

    public boolean lockInventory(long activityId);
}
