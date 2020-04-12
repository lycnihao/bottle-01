package run.bottle.app.code.repository;

import run.bottle.app.code.model.entity.User;
import run.bottle.app.code.repository.base.BaseRepository;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User, Integer> {
    /**
     * 获取用户
     *
     * @param username 用户名
     * @return 用户
     */
    Optional<User> findByUsername(String username);

    /**
     * 获取用户
     *
     * @param email 电子邮箱
     * @return 用户
     */
    Optional<User> findByEmail(String email);
}
