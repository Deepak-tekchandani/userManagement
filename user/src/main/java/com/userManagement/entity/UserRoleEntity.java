package com.userManagement.entity;

import javax.persistence.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_role")
public class UserRoleEntity {

    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    private Long id;

    //Relations
    @ManyToOne(fetch = FetchType.EAGER)
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    private RoleEntity roleEntity;

    public UserRoleEntity(UserEntity userEntity, RoleEntity roleEntity) {
        this.userEntity = userEntity;
        this.roleEntity = roleEntity;
    }
}
