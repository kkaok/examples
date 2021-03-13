package eblo.example.log.domain;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class HisLog {

    private String appType;
    private String targetId;
    private String reqType;
    private String reqIp;
    private String reqData;
    private LocalDateTime creteDt;
    private String creteId;
    
}
