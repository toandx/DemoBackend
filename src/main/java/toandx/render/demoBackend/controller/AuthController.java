package toandx.render.demoBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import toandx.render.demoBackend.dto.LoginRequest;
import toandx.render.demoBackend.dto.LoginResponse;
import toandx.render.demoBackend.dto.SignUpReq;
import toandx.render.demoBackend.entity.User;
import toandx.render.demoBackend.service.UserService;
import toandx.render.demoBackend.util.JwtTokenProvider;

@RestController
@RequestMapping("/auth")
//Step 7. Create AuthController, create login API, return JWT
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public LoginResponse authenticateUser(@RequestBody LoginRequest loginRequest) {

        // Xác thực từ username và password.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Trả về jwt cho người dùng.
        String jwt = tokenProvider.generateToken((User) authentication.getPrincipal());
        return new LoginResponse(jwt);
    }

    @PostMapping("/register")
    public String register(@RequestBody SignUpReq signUpReq) {
        userService.createUser(signUpReq.getUsername(),signUpReq.getPassword(),
                "user");
        return "Create User successfully";
    }
}
