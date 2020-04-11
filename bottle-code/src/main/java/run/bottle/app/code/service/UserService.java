package run.bottle.app.code.service;

import run.bottle.app.code.model.entity.User;
import run.bottle.app.code.service.base.CrudService;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Optional;

public interface UserService extends CrudService<User, Integer> {

    /**
     * 获取当前用户
     */
    @NonNull
    Optional<User> getCurrentUser();


    /**
     * 通过用户名获取用户
     *
     * @param username 用户名称
     * @return 用户信息
     */
    @NonNull
    Optional<User> getByUsername(@NonNull String username);

    /**
     * 通过用户名获取非null用户
     *
     * @param username 用户名称
     * @return 用户信息
     */
    @NonNull
    User getByUsernameOfNonNull(@NonNull String username);

    /**
     * 通过电子邮件获取用户
     *
     * @param email 邮箱
     * @return 用户信息
     */
    @NonNull
    Optional<User> getByEmail(@NonNull String email);

    /**
     * 通过电子邮件获取非null用户
     *
     * @param email email
     * @return 用户信息
     */
    @NonNull
    User getByEmailOfNonNull(@NonNull String email);


    /**
     * 用户不得过期
     *
     * @param user 用户信息
     */
    void mustNotExpire(@NonNull User user);

    /**
     * 检查密码是否与用户密码匹配
     *
     * @param user          用户信息
     * @param plainPassword 普通密码
     * @return 如果给定的密码与用户密码匹配，则为true；否则为假
     */
    boolean passwordMatch(@NonNull User user, @Nullable String plainPassword);
}
