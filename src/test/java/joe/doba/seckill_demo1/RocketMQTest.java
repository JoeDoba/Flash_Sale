package joe.doba.seckill_demo1;

import joe.doba.seckill_demo1.util.rocketMQ.RocketMQ_send_message;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class RocketMQTest {

    @Autowired
    private RocketMQ_send_message rocketMQSendmessage;

    @Test
    public void sendMQMessage() throws Exception {
        rocketMQSendmessage.sendMessage("test", "hahahahah" + new Date().toString());
    }

    @Test
    public void receiveMQMessage() throws Exception {
    }

}
