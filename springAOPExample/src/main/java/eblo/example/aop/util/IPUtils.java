package eblo.example.aop.util;

import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class IPUtils {

    private IPUtils() {
        throw new RuntimeException("This is a Util class. cannot create a new contructor");
    }

    // referred : https://stackoverflow.com/questions/1979419/how-to-get-client-ip-address-in-spring-bean
    private static final String[] IP_HEADER_CANDIDATES = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR"
    };

    public static String getRequestIPAddress(HttpServletRequest request) {
        String ip = null;
        if (request == null) {
            if (RequestContextHolder.getRequestAttributes() == null) {
                return null;
            }
            request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        }

        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if (!StringUtils.isEmpty(ip))
            return ip;

        for (String header : IP_HEADER_CANDIDATES) {
            String ipList = request.getHeader(header);
            if (ipList != null && ipList.length() != 0 && !"unknown".equalsIgnoreCase(ipList)) {
                return ipList.split(",")[0];
            }
        }

        return request.getRemoteAddr();
    }
}
