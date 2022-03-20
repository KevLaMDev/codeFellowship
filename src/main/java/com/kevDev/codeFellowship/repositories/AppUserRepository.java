package com.kevDev.codeFellowship.repositories;

import com.kevDev.codeFellowship.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    public UserDetails findByUsername(String username);
    public Boolean existsByUsername(String username);
}
