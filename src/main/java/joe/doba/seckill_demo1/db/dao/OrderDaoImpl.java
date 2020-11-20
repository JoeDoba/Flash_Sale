package joe.doba.seckill_demo1.db.dao;

import joe.doba.seckill_demo1.db.mappers.OrderMapper;
import joe.doba.seckill_demo1.db.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class OrderDaoImpl implements OrderDao{
    @Resource
    private OrderMapper orderMapper;

    @Override
    public void inertOder(Order order) {
        orderMapper.insert(order);
    }

    @Override
    public Order queryOrderById(Long orderId) {
        return orderMapper.selectByPrimaryKey(orderId);
    }

    @Override
    public Order queryOrderByOrderNum(String orderNo) {
        return orderMapper.selectByOrderNo(orderNo);
    }

    @Override
    public void updateOrder(Order order) {
        orderMapper.updateByPrimaryKey(order);
    }
}
