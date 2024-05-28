package com.userManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "role")
public class RoleEntity {

    @Id
    private Long id;

    @Column(name = "role_name")
    private String roleName;

    //Relations
    @OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL , mappedBy = "roleEntity")
    @JsonIgnore
    private Set<UserRoleEntity> userRoleEntities;
}
