package joe.doba.seckill_demo1.db.dao;

import joe.doba.seckill_demo1.db.pojo.Order;


public interface OrderDao {
    public void inertOder(Order order);

    public Order queryOrderById(Long orderId);

    public Order queryOrderByOrderNum(String orderNum);

    public void updateOrder(Order order);
}
