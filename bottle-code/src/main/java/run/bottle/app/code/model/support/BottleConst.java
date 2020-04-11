package run.bottle.app.code.model.support;

import org.springframework.http.HttpHeaders;

import java.time.Duration;

/**
 * 公共常量
 *
 * @author iksen
 * @date 2020/4/2 19:47
 */
public class BottleConst {


    /**
     * token标识名称
     */
    public final static String API_ACCESS_KEY_HEADER_NAME = "API-" + HttpHeaders.AUTHORIZATION;


    public final static Duration TEMP_TOKEN_EXPIRATION = Duration.ofDays(7);

    /**
     * user_session
     */
    public static String ADMIN_SESSION_KEY = "admin_session";

    /**
     * Squirrel Path.
     */
    public static final String Squirrel_PATH = "http://192.168.127.1:8090";

    /**
     * 路径分隔符
     */
    public static final String DELIMITER = "/";
}
