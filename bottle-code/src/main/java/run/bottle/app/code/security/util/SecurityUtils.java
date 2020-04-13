package run.bottle.app.code.security.util;

import run.bottle.app.code.model.entity.User;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

/**
 * Security utilities.
 *
 */
public class SecurityUtils {

    /**
     * Access token cache prefix.
     */
    private final static String TOKEN_ACCESS_CACHE_PREFIX = "bottle.admin.access.token.";

    /**
     * Refresh token cache prefix.
     */
    private final static String TOKEN_REFRESH_CACHE_PREFIX = "bottle.admin.refresh.token.";

    private final static String ACCESS_TOKEN_CACHE_PREFIX = "bottle.admin.access_token.";

    private final static String REFRESH_TOKEN_CACHE_PREFIX = "bottle.admin.refresh_token.";

    private final static String TEMP_TOKEN_CACHE_PREFIX = "bottle.temp.token.";


    private SecurityUtils() {
    }

    @NonNull
    public static String buildAccessTokenKey(@NonNull User user) {
        Assert.notNull(user, "User must not be null");

        return ACCESS_TOKEN_CACHE_PREFIX + user.getId();
    }

    @NonNull
    public static String buildRefreshTokenKey(@NonNull User user) {
        Assert.notNull(user, "User must not be null");

        return REFRESH_TOKEN_CACHE_PREFIX + user.getId();
    }

    @NonNull
    public static String buildTokenAccessKey(@NonNull String accessToken) {
        Assert.hasText(accessToken, "Access token must not be blank");

        return TOKEN_ACCESS_CACHE_PREFIX + accessToken;
    }

    @NonNull
    public static String buildTokenRefreshKey(@NonNull String refreshToken) {
        Assert.hasText(refreshToken, "Refresh token must not be blank");

        return TOKEN_REFRESH_CACHE_PREFIX + refreshToken;
    }

    @NonNull
    public static String buildTempTokenKey(@NonNull String tempToken) {
        Assert.hasText(tempToken, "Temporary token must not be blank");

        return TEMP_TOKEN_CACHE_PREFIX + tempToken;
    }
}
