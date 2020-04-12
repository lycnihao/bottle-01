package run.bottle.app.code.service.impl;

import cn.hutool.crypto.digest.BCrypt;
import run.bottle.app.code.exception.ForbiddenException;
import run.bottle.app.code.exception.NotFoundException;
import run.bottle.app.code.model.entity.User;
import run.bottle.app.code.repository.UserRepository;
import run.bottle.app.code.service.UserService;
import run.bottle.app.code.service.base.AbstractCrudService;
import run.bottle.app.code.utils.BottleUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl extends AbstractCrudService<User, Integer> implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }


    @Override
    public Optional<User> getCurrentUser() {
        // Find all users
        List<User> users = listAll();

        if (CollectionUtils.isEmpty(users)) {
            // Return empty user
            return Optional.empty();
        }

        // Return the first user
        return Optional.of(users.get(0));
    }

    @Override
    public Optional<User> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getByUsernameOfNonNull(String username) {
        return getByUsername(username).orElseThrow(() -> new NotFoundException("The username does not exist").setErrorData(username));
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getByEmailOfNonNull(String email) {
        return getByEmail(email).orElseThrow(() -> new NotFoundException("The email does not exist").setErrorData(email));
    }

    @Override
    public void mustNotExpire(User user) {
        Date now = new Date();
        if (user.getExpireTime() != null && user.getExpireTime().after(now)){
            long seconds = TimeUnit.MILLISECONDS.toSeconds(user.getExpireTime().getTime() - now.getTime());
            throw new ForbiddenException("账号已被停用，请 " + BottleUtils.timeFormat(seconds) + " 后重试").setErrorData(seconds);
        }
    }

    @Override
    public boolean passwordMatch(User user, String plainPassword) {
        Assert.notNull(user, "User must not be null");
        return !StringUtils.isBlank(plainPassword) && BCrypt.checkpw(plainPassword, user.getPassword());
    }
}
