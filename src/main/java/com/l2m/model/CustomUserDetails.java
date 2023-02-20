package com.l2m.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.l2m.domain.Authority;
import com.l2m.domain.Member;

import lombok.Getter;

@Getter
public class CustomUserDetails implements UserDetails {

    public CustomUserDetails(Member member) {
        this.id = member.getId();
        this.businessKey = member.getBusinessKey();
        this.username = member.getUsername();
        this.name = member.getName();
        this.password = member.getPassword();
        this.isFindPw = member.getIsFindPw();
        this.roles = member.getRoles();
        // new SimpleGrantedAuthority(member.getRoles().get(0).getName());
        this.authorities = Arrays.asList(new SimpleGrantedAuthority(member.getRoles().get(0).getName()));
    }

    private Long id;

    private String businessKey;
    
    private String username;
    
    private String name;

    private String password;

    private Character isFindPw;

    private List<Authority> roles;

    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.getPassword();
    }

    // @Override
    // public String getUsername() {
    //     return this.getUsername();
    // }

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
        return true;
    }
}
