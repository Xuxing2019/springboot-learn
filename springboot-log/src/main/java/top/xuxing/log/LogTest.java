package top.xuxing.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author xuxing
 * @date 2020/8/30
 */
@Component
public class LogTest {
    protected static final Log logger = LogFactory.getLog(LogTest.class);

    static {
        logger.error("error 静态代码块 日志信息");
        logger.info("info 静态代码块 日志信息");
    }

    @Bean
    public Target target(){
        logger.error("error 日志信息");
        logger.info("info 日志信息");
        return new Target();
    }
}
