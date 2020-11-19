package joe.doba.seckill_demo1.controllers;

import joe.doba.seckill_demo1.service.RedisOverSellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RedisOverSellControllorer {

    private RedisOverSellService redisOverSellService;
    public int successCnt;
    public int failCnt;
    @Autowired
    public RedisOverSellControllorer(RedisOverSellService redisOverSellService) {
        this.redisOverSellService = redisOverSellService;
        this.successCnt = 0;
        this.failCnt = 0;
    }

    @ResponseBody
    @RequestMapping("/seckill/{activityId}")
    public String seckillCommodity(@PathVariable Long activityId) {
        boolean purchaseResult = redisOverSellService.stockDeductValidator(activityId);
        return  purchaseResult ? "Congrats!!! You purchase successfully" : "Sorry, The items was sold out";
    }
}
