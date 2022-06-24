package com.booking.users;

import com.booking.exceptions.InvalidCurrentPasswordException;
import com.booking.exceptions.NewPasswordMatchedPreviousPasswordsException;
import com.booking.handlers.models.ErrorResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
@Validated
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
    @ApiOperation(value = "Change password")
    @ResponseStatus(code = HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Changed password successfully"),
            @ApiResponse(code = 401, message = "Unauthorized user", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "New password has incorrect format", response = ErrorResponse.class),
            @ApiResponse(code = 406, message = "Duplicate new password", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Something failed in the server", response = ErrorResponse.class)
    })
    String changePassword(Principal principal, @RequestParam("password") @ValidPassword String password,
                          @RequestParam("oldpassword") String oldPassword) throws InvalidCurrentPasswordException, NewPasswordMatchedPreviousPasswordsException {

        return userPrincipalService.changePassword(principal,oldPassword, password);
    }
}
