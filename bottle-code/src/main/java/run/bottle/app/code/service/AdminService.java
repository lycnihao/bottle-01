package run.bottle.app.code.service;


import run.bottle.app.code.security.AuthToken;

public interface AdminService {

    /**
     * Expired seconds.
     */
    int ACCESS_TOKEN_EXPIRED_SECONDS = 24 * 3600;

    int REFRESH_TOKEN_EXPIRED_DAYS = 30;

    AuthToken authenticate(String username, String password);

}
