package com.userManagement.security.controller;


import com.userManagement.security.JWTRequest;
import com.userManagement.security.JWTResponse;
import com.userManagement.security.UserDetailsServiceImpl;
import com.userManagement.security.config.JwtUtil;
import com.userManagement.security.helper.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticateController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;


    //generate Token
    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JWTRequest jwtRequest) throws Exception {
        try {

            authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());

        }catch (UserNotFoundException e){
            e.printStackTrace();
            throw new Exception("user not Found ! ");
        }
        /////////authenticate

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JWTResponse(token));

    }

    private void authenticate(String usename , String password)throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usename,password));

        }catch (DisabledException e){
//            e.printStackTrace();
            throw new Exception("USER DISABLEd"+e.getMessage());
        }catch (BadCredentialsException e){
            throw new Exception("Invalid Credentials "+e.getMessage());
        }

    }

}//end Class
