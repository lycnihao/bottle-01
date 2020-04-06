package com.couldr.squirrel.code.contorller.api;


import com.couldr.squirrel.code.model.support.BaseResponse;
import com.couldr.squirrel.code.model.support.UserDetail;
import com.couldr.squirrel.code.security.context.SecurityContextHolder;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User controller.
 *
 * @author iksen
 * @date 2020/4/6 21:32
 */
@RestController
@RequestMapping("/api/admin/users")
public class UserController {

    @GetMapping("info")
    @ApiOperation("Info")
    public BaseResponse getUserInfo() {
        UserDetail userDetail = SecurityContextHolder.getContext();
        if (!ObjectUtils.isEmpty(userDetail) && !ObjectUtils.isEmpty(userDetail.getUser())) {
            return BaseResponse.ok(userDetail.getUser());
        }
        return BaseResponse.ok(null);
    }
}
