package top.xuxing.elasticsearch.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xuxing
 * @date 2020/9/18
 * 1. 引入elasticsearch-high-level-client依赖
 * 2. springboot默认的elasticsearch版本可能不是我们想要的，注意修改(我说的是elasticsearch不是rest-high-level-client)
 * 3. 测试RestHighLevelClient是否注入成功
 */
@Configuration
public class ElasticSearchConfiguration {

    @Bean
    public RestHighLevelClient restHighLevelClient(){
        return new RestHighLevelClient(RestClient.builder(
                new HttpHost("192.168.56.10",9200)
        ));
    }
}
