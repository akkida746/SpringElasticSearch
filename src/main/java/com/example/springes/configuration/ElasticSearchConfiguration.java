package com.example.springes.configuration;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @author Akash Deep
 **/
@Slf4j
@Configuration
public class ElasticSearchConfiguration extends AbstractFactoryBean<RestHighLevelClient> {

    @Value("${spring.data.elasticsearch.cluster-nodes}")
    private String clusterNodes;
    @Value("${spring.data.elasticsearch.cluster-name}")
    private String clusterName;

    private static final String ENDPOINT = "";

    private RestHighLevelClient restHighLevelClient;

    @Override
    public void destroy(){
        try{
            if(restHighLevelClient != null)
                restHighLevelClient.close();
        } catch (IOException e) {
            log.error("Error while closing ElasticSearch client");
        }
    }

    @Override
    public Class<RestHighLevelClient> getObjectType() {
        return RestHighLevelClient.class;
    }

    @Override
    public boolean isSingleton(){
        return false;
    }

    @Override
    protected RestHighLevelClient createInstance() throws Exception {
        return buildClient();
    }

    private RestHighLevelClient buildClient(){
        try{
            restHighLevelClient = new RestHighLevelClient(
                    RestClient.builder(
                            new HttpHost(ENDPOINT, 9200, "http"),
                            new HttpHost(ENDPOINT, 9201, "http")));
        } catch (Exception e){
            log.error(e.getMessage());
        }

        return restHighLevelClient;
    }
}
