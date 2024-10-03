package com.deepanshu.dairydriveapi.entities;

import com.deepanshu.dairydriveapi.utilities.UsersRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Distributor implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    //@Pattern(regexp = )
    private String password;

    @Column(nullable = false)
    private Integer deliveryCode;

    @Column(nullable = false)
    private UsersRole role;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 20, nullable = false, unique = true)
    private String contact;

    @Column(nullable = false)
    private int daysRemaining;// Number of days left for out of valid plan.

    @Column(nullable = false)
    private boolean isDeleted;

    private String emailToken;
    private boolean isVerified;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String roleName = "ROLE_"+this.getRole().name();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(roleName);
        return List.of(authority);
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }
}
