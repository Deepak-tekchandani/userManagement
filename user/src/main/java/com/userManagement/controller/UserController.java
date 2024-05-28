package com.userManagement.controller;


import com.userManagement.entity.RoleEntity;
import com.userManagement.entity.UserEntity;
import com.userManagement.entity.UserRoleEntity;
import com.userManagement.security.helper.UserFoundException;
import com.userManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/create")
    public UserEntity create(@RequestBody UserEntity userEntity) throws Exception {

        //encodeing the password by BCryptPasswordEncoder;
        userEntity.setPassword(this.passwordEncoder.encode(userEntity.getPassword()));


        Set<UserRoleEntity> roles = new HashSet<>();
        RoleEntity role = new RoleEntity();
        role.setId(114L);
        role.setRoleName("ADMIN");

        UserRoleEntity userRole = new UserRoleEntity();
        userRole.setUserEntity(userEntity);
        userRole.setRoleEntity(role);

        roles.add(userRole);

        return userService.createUser(userEntity,roles);
    }
    @PutMapping("/update")
    public UserEntity upddate(@RequestBody UserEntity userEntity)throws Exception {

        //encodeing the password by BCryptPasswordEncoder;
        if(userEntity.getPassword() != null && !userEntity.getPassword().isEmpty()){
            userEntity.setPassword(this.passwordEncoder.encode(userEntity.getPassword()));
        }
        return userService.updateUser(userEntity);
    }

    @GetMapping("/{username}")
    public UserEntity getUserByUsername(@PathVariable("username") String username){
        return this.userService.getUserByUsername(username);

    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable("userId") Long userId){
        this.userService.deleteUser(userId);
        return "Deleted";
    }

    @GetMapping("/getAll")
    public List<UserEntity> getAll(){
        List<UserEntity> userEntities =userService.findAll();
        return userEntities;
    }

    @ExceptionHandler(UserFoundException.class)
    public ResponseEntity<?> exceptionHandler(UserFoundException ex){
        ex.printStackTrace();
        return ResponseEntity.badRequest().body(ex.getMessage());
    }


}
