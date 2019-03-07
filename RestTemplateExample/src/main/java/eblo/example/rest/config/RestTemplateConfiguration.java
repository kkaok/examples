package eblo.example.rest.config;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import eblo.example.rest.config.HttpHostsConfiguration.HttpHostConfiguration;

@Configuration
public class RestTemplateConfiguration {

    private HttpHostsConfiguration httpHostConfiguration;
    
    @Autowired
    public void setHttpHostsConfiguration(HttpHostsConfiguration httpHostConfiguration) {
        this.httpHostConfiguration = httpHostConfiguration;
    }
    
    @Bean
    public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {
        PoolingHttpClientConnectionManager result = new PoolingHttpClientConnectionManager();
        result.setMaxTotal(this.httpHostConfiguration.getMaxTotal());
        // Default max per route is used in case it's not set for a specific route
        result.setDefaultMaxPerRoute(this.httpHostConfiguration.getDefaultMaxPerRoute());
        // and / or
        if (!CollectionUtils.isEmpty(this.httpHostConfiguration.getMaxPerRoutes())) {
            for (HttpHostConfiguration httpHostConfig : this.httpHostConfiguration.getMaxPerRoutes()) {
                HttpHost host = new HttpHost(httpHostConfig.getHost(), httpHostConfig.getPort(),
                        httpHostConfig.getScheme());
                // Max per route for a specific host route
                result.setMaxPerRoute(new HttpRoute(host), httpHostConfig.getMaxPerRoute());
            }
        }
        return result;
    }

    @Bean
    public RequestConfig requestConfig() {
        return RequestConfig.custom()
                .setConnectionRequestTimeout(httpHostConfiguration.getConnectionRequestTimeout())
                .setConnectTimeout(httpHostConfiguration.getConnectionTimeout())
                .setSocketTimeout(httpHostConfiguration.getSocketTimeout())
                .build();
    }

    @Bean
    public CloseableHttpClient httpClient() {
        return HttpClientBuilder.create().setConnectionManager(poolingHttpClientConnectionManager())
                .setDefaultRequestConfig(requestConfig()).build();
    }

    @Bean
    public RestTemplate restTemplate() {
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient());
        return new RestTemplate(requestFactory);
    }
}
