package com.example.demo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class LekarzDetails implements UserDetails {

    private Lekarz lekarz;


    public LekarzDetails(Lekarz lekarz) {
        this.lekarz = lekarz;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return lekarz.getHasloLekarz();
    }

    @Override
    public String getUsername() {
        return lekarz.getEmailLekarz();
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
        return true;
    }

    public String getFullName() {
        return lekarz.getImieLekarz() + " " + lekarz.getNazwiskoLekarz();
    }

}
