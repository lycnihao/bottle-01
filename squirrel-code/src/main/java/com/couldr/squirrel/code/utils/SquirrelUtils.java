package com.couldr.squirrel.code.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * Squirrel Utils
 *
 * @Author: iksen
 * @Date: 2020/4/2 21:37
 */
public class SquirrelUtils {

    /**
     * 获取带有破折号的随机uuid。
     * @return 带有破折号的随机uuid
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 获取不带破折号的随机uuid。
     * @return 无破折号的随机uuid
     */
    public static String randomUUIDWithoutDash() {
        return StringUtils.remove(UUID.randomUUID().toString(), '-');
    }
}
