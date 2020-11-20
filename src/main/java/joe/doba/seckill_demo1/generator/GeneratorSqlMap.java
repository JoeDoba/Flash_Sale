package joe.doba.seckill_demo1.generator;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class GeneratorSqlMap {
    public void generator() throws Exception {
        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;
//        String realpath = System.getProperty("user.dir");
//        String path = GeneratorSqlMap.class.getClassLoader().getResource("generatorConfig.xml").getPath();
//        System.out.println(path);
        File configFile = new File("D:/Work/seckill_demo1/src/main/resources/generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }

    public static void main(String[] args) throws Exception {
        try {
            GeneratorSqlMap generatorSqlmap = new GeneratorSqlMap();
            generatorSqlmap.generator();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
