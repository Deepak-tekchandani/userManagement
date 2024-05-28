package com.userManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.userManagement.security.Authority;
import javax.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "user")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_name")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastname;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "enable")
    private Boolean enable=true;

    //Relations

    @OneToMany(fetch = FetchType.EAGER , cascade = CascadeType.ALL , mappedBy = "userEntity")
    @JsonIgnore
    private Set<UserRoleEntity> userRoleEntities;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Authority> set = new HashSet<>();
        this.userRoleEntities.forEach(userRoleEntity -> {
            if (userRoleEntity != null && userRoleEntity.getRoleEntity() != null) {
                set.add(new Authority(userRoleEntity.getRoleEntity().getRoleName()));
            }
        });
        return set;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }
}
