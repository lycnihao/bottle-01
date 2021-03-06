package run.bottle.app.upload.utils;

import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

/**
 * Squirrel Utils
 *
 * @Author: iksen
 * @Date: 2020/4/2 21:37
 */
public class BottleFileUtils {

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

    public static String getPrefix(String fileName) {
        int suffixFirstIndex = fileName.lastIndexOf(".");
        if (suffixFirstIndex == -1){
            return StringUtils.EMPTY;
        }
        return getSuffixBySlash(fileName.substring(0, suffixFirstIndex));
    }

    public static String getSuffix(String fileName) {
        int suffixFirstIndex = fileName.lastIndexOf(".");
        if (suffixFirstIndex == -1){
            return StringUtils.EMPTY;
        }
        return fileName.substring(suffixFirstIndex + 1);
    }

    public static String getSuffixBySlash(String fileName) {
        int slash =  fileName.lastIndexOf("/");
        if (slash != -1) {
            return fileName.substring(slash + 1);
        }
        return fileName;
    }

    public static String getPrefixBySlash(String fileName) {
        int slash =  fileName.lastIndexOf("/");
        if (slash == -1) {
            return StringUtils.EMPTY;
        }
        return fileName.substring(0 , slash);
    }
}
