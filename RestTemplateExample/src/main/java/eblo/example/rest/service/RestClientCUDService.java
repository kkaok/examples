package eblo.example.rest.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import eblo.example.rest.domain.RestParams;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RestClientCUDService extends RestClientCommonService{

    @Override
    protected String getForwardingUrl(RestParams pRestParams) {
        return makeUrl(pRestParams.getUrlInfo(), pRestParams.getPathInfo());
    }

    @Override
    protected HttpEntity<String> getHttpEntity(RestParams pRestParams, HttpHeaders headers) {
        if(pRestParams.getRequestBody() != null)
            return new HttpEntity<>(pRestParams.getRequestBody(), headers);
        else return new HttpEntity<>(headers);
    }

}
