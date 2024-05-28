package com.userManagement.service;


import com.userManagement.entity.UserEntity;
import com.userManagement.entity.UserRoleEntity;

import java.util.List;
import java.util.Set;

public interface UserService {

    public UserEntity createUser(UserEntity userEntity, Set<UserRoleEntity> userRoleEntities) throws Exception;

    public UserEntity updateUser(UserEntity userEntity)throws Exception;

    public UserEntity getUserByUsername(String username);

    public void deleteUser(Long userId);

    List<UserEntity> findAll();
}
