package joe.doba.seckill_demo1.db.dao;
import joe.doba.seckill_demo1.db.mappers.CommodityMapper;
import joe.doba.seckill_demo1.db.pojo.Commodity;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class CommodityDaoImpl implements CommodityDao {

    @Resource
    private CommodityMapper commodityMapper;

    @Override
    public Commodity queryCommodityById(long commodityId) {
        return commodityMapper.selectByPrimaryKey(commodityId);
    }
}
