package joe.doba.seckill_demo1.service;

import com.alibaba.fastjson.JSON;
import joe.doba.seckill_demo1.db.dao.ActivityDao;
import joe.doba.seckill_demo1.db.dao.OrderDao;
import joe.doba.seckill_demo1.db.pojo.Activity;
import joe.doba.seckill_demo1.db.pojo.Order;
import joe.doba.seckill_demo1.util.Redis;
import joe.doba.seckill_demo1.util.SnowFlake;
import joe.doba.seckill_demo1.util.rocketMQ.RocketMQ_send_message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;


@Slf4j
@Service
public class RedisOverSellService {

    private Redis redisService;
    private OrderDao orderDao;
    private ActivityDao activityDao;
    private RocketMQ_send_message rocketMQSendmessage;
    private SnowFlake snowFlake = new SnowFlake(1,1);

    @Autowired
    public RedisOverSellService(Redis redisService, OrderDao orderDao, ActivityDao activityDao, RocketMQ_send_message rocketMQSendmessage) {
        this.redisService = redisService;
        this.orderDao = orderDao;
        this.activityDao = activityDao;
        this.rocketMQSendmessage = rocketMQSendmessage;
    }

    public boolean stockDeductValidator(Long activityId) {
        String key = "stock:" + activityId;
        return redisService.stockDeducValidator(key);
    }

    public Order createOrder(Long activityId, Long userId) throws Exception {
        Activity activity = activityDao.queryActivityById(activityId);
        Order order = new Order();
        /**
         * Create order number using Snowflake algorithm
         **/
        order.setOrderNo(String.valueOf(snowFlake.nextId()));
        order.setSeckillActivityId(activityId);
        order.setUserId(userId);
        order.setOrderAmount(activity.getSeckillPrice());
        /**
         * Apply rocketMQ message queue to buffer order creation request
         **/
        rocketMQSendmessage.sendMessage("Create_Order", JSON.toJSONString(order));

        return order;
    }

    public void payOrder(String orderNo) {
        log.info("Payment Finished. Order Number: " + orderNo);
        Order order = orderDao.queryOrderByOrderNum(orderNo);
        boolean deductInventoryResult = activityDao.deductInventory(order.getSeckillActivityId());
        if (deductInventoryResult) {
            order.setPayTime(new Date());
            order.setOrderStatus(2);
            orderDao.updateOrder(order);
        }
    }
}
