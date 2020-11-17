package joe.doba.seckill_demo1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@ComponentScan(basePackages = {"joe.doba"})
@SpringBootApplication
@MapperScan("joe.doba.seckill_demo1.db.mappers")
public class SeckillDemo1Application {

    public static void main(String[] args) {
        SpringApplication.run(SeckillDemo1Application.class, args);
    }

}
