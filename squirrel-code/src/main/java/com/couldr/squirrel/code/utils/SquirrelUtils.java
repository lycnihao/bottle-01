package com.couldr.squirrel.code.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

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

    /**
     * 时间格式化
     *
     * @param totalSeconds 秒
     * @return 格式化时间
     */
    @NonNull
    public static String timeFormat(long totalSeconds) {
        if (totalSeconds <= 0) {
            return "0 second";
        }

        StringBuilder timeBuilder = new StringBuilder();

        long hours = totalSeconds / 3600;
        long minutes = totalSeconds % 3600 / 60;
        long seconds = totalSeconds % 3600 % 60;

        if (hours > 0) {
            if (StringUtils.isNotBlank(timeBuilder)) {
                timeBuilder.append(", ");
            }
            timeBuilder.append(pluralize(hours, "hour", "hours"));
        }

        if (minutes > 0) {
            if (StringUtils.isNotBlank(timeBuilder)) {
                timeBuilder.append(", ");
            }
            timeBuilder.append(pluralize(minutes, "minute", "minutes"));
        }

        if (seconds > 0) {
            if (StringUtils.isNotBlank(timeBuilder)) {
                timeBuilder.append(", ");
            }
            timeBuilder.append(pluralize(seconds, "second", "seconds"));
        }

        return timeBuilder.toString();
    }

    /**
     * 多元化时间标签格式。
     *
     * @param times       times
     * @param label       label
     * @param pluralLabel plural label
     * @return pluralized format
     */
    @NonNull
    public static String pluralize(long times, @NonNull String label, @NonNull String pluralLabel) {
        Assert.hasText(label, "Label must not be blank");
        Assert.hasText(pluralLabel, "Plural label must not be blank");

        if (times <= 0) {
            return "no " + pluralLabel;
        }

        if (times == 1) {
            return times + " " + label;
        }

        return times + " " + pluralLabel;
    }
}
