package joe.doba.seckill_demo1.db.web;

import joe.doba.seckill_demo1.db.service.OverSellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OverSellController {
    @Autowired
    private OverSellService overSellService;
    @ResponseBody
    @RequestMapping("/seckil/{activeID}")
    public String checkOverSell(@PathVariable long activeID) {
        return overSellService.processSecKill(activeID);
    }
}
