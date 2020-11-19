package joe.doba.seckill_demo1.db.dao;

import joe.doba.seckill_demo1.db.pojo.Commodity;

public interface CommodityDao {

    public Commodity queryCommodityById(long commodityId);
}
