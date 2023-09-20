package top.integer.blog.utils;


import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.lionsoul.ip2region.xdb.Searcher;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 网络工具类
 *
 * @author https://github.com/liyupi
 */
@Slf4j
@Data
public class IpUtils {
    private static Pattern PATTERN = Pattern.compile("^(?:(?:25[0-5]|2[0-4]\\d|[01]?\\d{1,2})|\\*)\\.(?:(?:25[0-5]|2[0-4]\\d|[01]?\\d{1,2})|\\*)\\.(?:(?:25[0-5]|2[0-4]\\d|[01]?\\d{1,2})|\\*)\\.(?:(?:25[0-5]|2[0-4]\\d|[01]?\\d{1,2})|\\*)$");

    private volatile String path;

    private volatile static String dbPath;
    private static String LOCALHOST = "127.0.0.1";


    /**
     * 判断是否是合法的IP
     *
     * @param ip - IP地址
     * @return
     */
    public static boolean isLegalIp(String ip) {
        return PATTERN.matcher(ip).matches();
    }

    /**
     * 获取客户端 IP 地址
     *
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if (LOCALHOST.equals(ip)) {
                // 根据网卡取本机配置的 IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (inet != null) {
                    ip = inet.getHostAddress();
                }
            }
        }
        // 多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(',') > 0) {
                ip = ip.substring(0, ip.indexOf(','));
            }
        }
        if (ip == null) {
            return LOCALHOST;
        }

        if (!isLegalIp(ip)) {
            return LOCALHOST;
        }

        return ip;
    }

    public static String getIp() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        return getIpAddress(request);
    }

    public static long getIpLong() {

        String ip = getIp();
        if (ip.contains(":")) {
            return 2130706433L;
        }
        String[] split = ip.split("\\.");
        if (split.length != 4) {
            return 2130706433L;
        }
        long result = 0;
        for (var item : split) {
            int octet = Integer.parseInt(item); // 将每个部分转换为整数
            if (octet < 0 || octet > 255) {
                return 2130706433L;
            }
            result = (result << 8) | octet;
        }
        return result;
    }


    public static Map<String, String> getRegion(List<String> ip) {

        Searcher searcher = null;
        try {
            searcher = Searcher.newWithFileOnly(dbPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Searcher finalSearcher = searcher;
            return ip.stream().distinct().collect(Collectors.toMap(s -> s, s -> {
                try {
                    String result = finalSearcher.search(s);
                    if (result.startsWith("中国")) {
                        String[] split = result.split("\\|");
                        if (split.length > 2) {
                            return split[2];
                        } else {
                            return "中国";
                        }
                    } else if (result.startsWith("0")) {
                        return "内网";
                    } else {
                        return result.split("\\|")[0];
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }));
        } catch (Exception e) {
            // 3、关闭资源
            try {
                searcher.close();
            } catch (IOException ee) {
                throw new RuntimeException(ee);
            }

        }
        return ip.stream().distinct().collect(Collectors.toMap(s -> s, s -> "未知"));
    }

    private void setPath() {
        IpUtils.dbPath = path;
        log.info("路径位置：" + path);
    }

}
