package eblo.example.log.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import eblo.example.log.domain.AppType;
import eblo.example.log.domain.HisLog;
import eblo.example.log.domain.ReqType;
import eblo.example.log.domain.TargetId;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HisLogService {

    
    //@Async
    public void save(final String reqIp, AppType appType, TargetId targetId, ReqType reqType, Object params) {
        try {
            HisLog hisLog = new HisLog();
            hisLog.setAppType(appType.name());
            hisLog.setTargetId(targetId.name());
            hisLog.setReqType(reqType.getValue());
            hisLog.setReqIp(reqIp);
            hisLog.setCreteDt(LocalDateTime.now());
            hisLog.setReqData(convertJson(params));
            log.info(hisLog.toString());
        }catch(Exception e) {
            log.error(e.getLocalizedMessage());
        }
    }
    
    public static String convertJson(Object obj) {
        if(obj == null) return null;
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(Include.NON_NULL);
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
