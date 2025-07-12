package com.StackIt.StackIt.Security;


import com.StackIt.StackIt.models.User;
import com.StackIt.StackIt.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private JWThelper helper;
    @Autowired
    UserRepo userRepo;
    @PostMapping("/register")
    public String register(@RequestBody User user){
        userRepo.save(user);
        return "success";
    }


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody  JwtRequest request) {
        this.doAuthenticate(request.getUserName(), request.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUserName());
        String token = this.helper.generateToken(userDetails);
        JwtResponse response = new JwtResponse();
        response.setJwtToken(token);
        response.setUserName(request.getUserName());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String username, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new RuntimeException("Invalid Username or Password  !!");
        }
    }


}
