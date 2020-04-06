package com.couldr.squirrel.code.model.support;

import com.couldr.squirrel.code.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 用户详细资料
 *
 * @author iksen
 */
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class UserDetail {

    private User user;


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
        this.user.setPassword("***");
    }
}
