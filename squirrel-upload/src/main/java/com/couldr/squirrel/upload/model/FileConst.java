package com.couldr.squirrel.upload.model;

import java.time.Duration;
import org.springframework.http.HttpHeaders;

/**
 * 公共常量
 *
 * @author iksen
 * @date 2020/4/8 15:07
 */
public class FileConst {

    /**
     * 用户主目录
     */
    public final static String USER_HOME = System.getProperties().getProperty("user.home");

    /**
     * 临时目录
     */
    public final static String TEMP_DIR = System.getProperties().getProperty("java.io.tmpdir");

    /**
     * 路径分隔符
     */
    public static final String DELIMITER = "/";
}
