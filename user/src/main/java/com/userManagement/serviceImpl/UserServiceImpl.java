package com.userManagement.serviceImpl;

import com.userManagement.entity.UserEntity;
import com.userManagement.entity.UserRoleEntity;
import com.userManagement.repository.RoleRepository;
import com.userManagement.repository.UserRepository;
import com.userManagement.security.helper.UserFoundException;
import com.userManagement.security.helper.UserNotFoundException;
import com.userManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserEntity createUser(UserEntity userEntity, Set<UserRoleEntity> userRoleEntities) throws Exception {

        UserEntity local = this.userRepository.findByUsername(userEntity.getUsername());
        if (local!=null){
            throw new UserFoundException();
        }else
        {
            //userCreate
            for(UserRoleEntity ur:userRoleEntities){
                roleRepository.save(ur.getRoleEntity());
            }
            userEntity.setUserRoleEntities(userRoleEntities);
            local=userRepository.save(userEntity);

        }

        return local;
    }

    @Override
    public UserEntity updateUser(UserEntity userEntity)throws Exception {
        if(userEntity.getId() != null){
          Optional<UserEntity> user = userRepository.findById(userEntity.getId());
          if (user.isEmpty()){
              throw new UserNotFoundException();
          }else{

              userEntity.setUserRoleEntities(user.get().getUserRoleEntities());
              userEntity =userRepository.save(userEntity);
          }
          return userEntity;
        }
        return null;
    }

    @Override
    public UserEntity getUserByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }


}
