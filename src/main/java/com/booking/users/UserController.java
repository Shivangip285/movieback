package com.booking.users;

import com.booking.exceptions.InvalidOldPasswordException;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "Users")
@RestController
public class UserController {

    private final UserPrincipalService userPrincipalService;

    @Autowired
    UserController(UserPrincipalService userPrincipalService){
        this.userPrincipalService = userPrincipalService;
    }

    @GetMapping("/login")
    Map<String, Object> login(Principal principal) {
        String username = principal.getName();
        Map<String, Object> userDetails = new HashMap<>();
        userDetails.put("username", username);
        return userDetails;
    }

    @PreAuthorize("hasRole('READ_PRIVILEGE')")
    @PostMapping("/changePassword")
    String changePassword(Principal principal, @RequestParam("password") String password,
                          @RequestParam("oldpassword") String oldPassword) throws InvalidOldPasswordException {

        return userPrincipalService.changePassword(principal,oldPassword, password);
    }
}
