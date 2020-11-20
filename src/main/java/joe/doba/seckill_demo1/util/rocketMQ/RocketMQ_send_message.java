package joe.doba.seckill_demo1.util.rocketMQ;

import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RocketMQ_send_message {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void sendMessage(String topic, String body) throws Exception{
        Message message = new Message(topic, body.getBytes());
        rocketMQTemplate.getProducer().send(message);
    }
}
