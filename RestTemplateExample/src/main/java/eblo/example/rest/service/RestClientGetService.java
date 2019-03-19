package eblo.example.rest.service;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import eblo.example.rest.domain.RestParams;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RestClientGetService extends RestClientCommonService{

    @Override
    protected String getForwardingUrl(RestParams pRestParams) {
        UriComponentsBuilder componentsBuilder = UriComponentsBuilder
                .fromHttpUrl(makeUrl(pRestParams.getUrlInfo(), pRestParams.getPathInfo()));
        for (Map.Entry<String, String> entry : pRestParams.getParameters().entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            componentsBuilder.queryParam(key, value);
        }
        
        UriComponents builder = componentsBuilder.build();
        return builder.toUriString();
    }

    @Override
    protected HttpEntity<?> getHttpEntity(RestParams pRestParams, HttpHeaders headers) {
        return new HttpEntity<>(headers);
    }

}
