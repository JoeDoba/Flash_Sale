package joe.doba.seckill_demo1.util.rocketMQ;

import com.alibaba.fastjson.JSON;
import joe.doba.seckill_demo1.db.dao.ActivityDao;
import joe.doba.seckill_demo1.db.dao.OrderDao;
import joe.doba.seckill_demo1.db.pojo.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Slf4j
@Service
@RocketMQMessageListener(topic = "Create_Order", consumerGroup = "Lock_Order_Group")
public class RocketMQ_message_parse implements RocketMQListener<MessageExt> {

    private OrderDao orderDao;
    private ActivityDao activityDao;

    @Autowired
    public RocketMQ_message_parse(OrderDao orderDao, ActivityDao activityDao) {
        this.orderDao = orderDao;
        this.activityDao = activityDao;
    }

    @Override
    @Transactional
    public void onMessage(MessageExt messageExt) {
        /**
         * Parse order inform from generator
         **/
        String message = new String(messageExt.getBody(), StandardCharsets.UTF_8);
        log.info("Receive order request: " + message);
        Order order = JSON.parseObject(message, Order.class);
        order.setCreateTime(new Date());
        /**
         * Check and lock inventory
         **/
        boolean lockInventoryResult = activityDao.lockInventory(order.getSeckillActivityId());
        // OrderStatus 0: No inventory 1: Inventory available & Wait for payment 2: Payment finished
        if (lockInventoryResult) {
            order.setOrderStatus(1);
        } else {
            order.setOrderStatus(0);
        }
        /**
         * Insert Order to JDBC
         */
        orderDao.inertOder(order);
    }
}
