package joe.doba.seckill_demo1.util.rocketMQ;

import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
@RocketMQMessageListener(topic = "test", consumerGroup = "consumerGroup")
public class RocketMQ_message_listener implements RocketMQListener<MessageExt> {
    @Override
    public void onMessage(MessageExt messageExt) {
        try {
            String body = new String(messageExt.getBody(), "UTF-8");
            System.out.println("Receive message: " + body);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
