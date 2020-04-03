package com.couldr.squirrel.code.service.impl;

import cn.hutool.core.lang.Validator;
import com.couldr.squirrel.cache.LocalCache;
import com.couldr.squirrel.code.exception.BadRequestException;
import com.couldr.squirrel.code.exception.NotFoundException;
import com.couldr.squirrel.code.model.entity.User;
import com.couldr.squirrel.code.security.AuthToken;
import com.couldr.squirrel.code.service.AdminService;
import com.couldr.squirrel.code.service.UserService;
import com.couldr.squirrel.code.utils.SquirrelUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserService userService;

    private final LocalCache localCache;

    public AdminServiceImpl(UserService userService, LocalCache localCache) {
        this.userService = userService;
        this.localCache = localCache;
    }


    @Override
    public AuthToken authenticate(String username, String password) {
        System.out.println(username);
        String mismatchTip = "用户名或者密码不正确";

        final User user;

        try {
            // Get user by username or email
            user = Validator.isEmail(username) ?
                    userService.getByEmailOfNonNull(username) : userService.getByUsernameOfNonNull(username);
        } catch (NotFoundException e) {
            throw new BadRequestException(mismatchTip);
        }

        userService.mustNotExpire(user);

        if (!userService.passwordMatch(user, password)) {
            // If the password is mismatch
            System.out.println("账号或密码不匹配");
            throw new BadRequestException(mismatchTip);
        }
        System.out.println("登录成功");
        return  buildAuthToken(user);
    }

    /**
     * Builds authentication token.
     *
     * @param user user info must not be null
     * @return authentication token
     */
    @NonNull
    private AuthToken buildAuthToken(@NonNull User user) {
        Assert.notNull(user, "User must not be null");

        // Generate new token
        AuthToken token = new AuthToken();

        token.setAccessToken(SquirrelUtils.randomUUIDWithoutDash());
        token.setExpiredIn(ACCESS_TOKEN_EXPIRED_SECONDS);
        token.setRefreshToken(SquirrelUtils.randomUUIDWithoutDash());

/*        // Cache those tokens, just for clearing
        localCache.put(SecurityUtils.buildAccessTokenKey(user), token.getAccessToken(), ACCESS_TOKEN_EXPIRED_SECONDS);
        localCache.put((SecurityUtils.buildRefreshTokenKey(user), token.getRefreshToken(), REFRESH_TOKEN_EXPIRED_DAYS);

        // Cache those tokens with user id
        localCache.put((SecurityUtils.buildTokenAccessKey(token.getAccessToken()), user.getId(), ACCESS_TOKEN_EXPIRED_SECONDS);
        localCache.put((SecurityUtils.buildTokenRefreshKey(token.getRefreshToken()), user.getId(), REFRESH_TOKEN_EXPIRED_DAYS);*/

        return token;
    }
}
