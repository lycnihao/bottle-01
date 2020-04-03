package com.couldr.squirrel.code.contorller.api;

import com.couldr.squirrel.code.model.params.LoginParam;
import com.couldr.squirrel.code.security.AuthToken;
import com.couldr.squirrel.code.service.AdminService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    @PostMapping("login")
    @ApiOperation("Login")
    public AuthToken auth(@RequestBody @Valid LoginParam loginParam) {
        return adminService.authenticate(loginParam.getUsername(), loginParam.getPassword());
    }

}
