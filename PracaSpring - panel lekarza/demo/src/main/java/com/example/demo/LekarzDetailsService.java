package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class LekarzDetailsService implements UserDetailsService {

    @Autowired
    private LekarzRepository lekarzRepository;

    @Override
    public UserDetails loadUserByUsername(String emailLekarz) throws UsernameNotFoundException {
        Lekarz lekarz = lekarzRepository.findByEmail(emailLekarz);
        if (lekarz == null)
        {
            throw new UsernameNotFoundException("Nie znaleziono uzytkownika");
        }
        return new LekarzDetails(lekarz);
    }
}
