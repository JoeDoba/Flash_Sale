package joe.doba.seckill_demo1.controllers;

import joe.doba.seckill_demo1.db.dao.ActivityDao;
import joe.doba.seckill_demo1.db.dao.OrderDao;
import joe.doba.seckill_demo1.db.pojo.Activity;
import joe.doba.seckill_demo1.db.pojo.Order;
import joe.doba.seckill_demo1.service.RedisOverSellService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Controller
public class OrderRequestController {

    private RedisOverSellService redisOverSellService;
    private OrderDao orderDao;
    private ActivityDao activityDao;

    @Autowired
    public OrderRequestController(RedisOverSellService redisOverSellService, OrderDao orderDao, ActivityDao activityDao) {
        this.redisOverSellService = redisOverSellService;
        this.orderDao = orderDao;
        this.activityDao = activityDao;
    }

    @RequestMapping("seckill/buy/{userId}/{activityId}")
    public ModelAndView seckillCommodity(@PathVariable Long userId, @PathVariable Long activityId) {
        boolean purchaseResult = false;
        ModelAndView modelAndView = new ModelAndView();
        try{
            purchaseResult = redisOverSellService.stockDeductValidator(activityId);
            if (purchaseResult) {
                Order order = redisOverSellService.createOrder(activityId, userId);
                modelAndView.addObject("resultInfo", "Congrats!! The order has been generated. Order Number: " + order.getOrderNo());
                modelAndView.addObject("orderNo", order.getOrderNo());
                modelAndView.addObject("userId", userId);
            } else {
                modelAndView.addObject("resultInfo", "Sorry, the item was sold out");
            }
        } catch (Exception e) {
            log.error("System Error: " + e.toString());
            modelAndView.addObject("resultInfo", "System Error");
        }
        modelAndView.setViewName("seckill_result");
        return modelAndView;
    }

    @RequestMapping("seckill/orderQuery/{userId}/{orderNo}")
    public ModelAndView orderQuery(@PathVariable String userId, @PathVariable String orderNo) {
        log.info("Order Detail");
        log.info("Order Number: " + orderNo);
        Order order = orderDao.queryOrderByOrderNum(orderNo);
        ModelAndView modelAndView = new ModelAndView();

        if (order != null) {
            modelAndView.setViewName("order");
            modelAndView.addObject("order", order);
            Activity activity = activityDao.queryActivityById(order.getSeckillActivityId());
            modelAndView.addObject("activity", activity);
        } else {
            modelAndView.setViewName("order_wait");
            modelAndView.addObject("orderNo", orderNo);
        }
        return modelAndView;
    }

    @RequestMapping("/seckill/payOrder/{orderNo}")
    public ModelAndView payment(@PathVariable String orderNo) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("payment_finished");
        modelAndView.addObject("orderNo", orderNo);
        redisOverSellService.payOrder(orderNo);
        return modelAndView;
    }

    @RequestMapping("/seckill/getSystemTime")
    public String getSystemTime() {
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = time.format(new Date());
        return date;
    }
}
