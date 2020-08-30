package top.xuxing;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



/**
 * @author xuxing
 * @date 2020/8/30
 * @desc Springboot default use logback
 */
@SpringBootApplication
public class LogApplication {
    protected static final Log logger = LogFactory.getLog(LogApplication.class);
    public static void main(String[] args) {
        /**
         * 请沙雕不要在SpringBoot 初始化之前进行一个日志输出 此时日志是不受控制的，你日志等级控制都是在application.yml中配置的，
         * 你日志输出控制,还想超出springboot的控制那是不可能的
         */
        SpringApplication.run(LogApplication.class);
        logger.error("error 日志信息");
        logger.info("info 日志信息");
    }
}
