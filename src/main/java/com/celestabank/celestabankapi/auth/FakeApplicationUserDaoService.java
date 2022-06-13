package com.celestabank.celestabankapi.auth;

import lombok.AllArgsConstructor;
import org.assertj.core.util.Lists;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.celestabank.celestabankapi.config.Security.config.ApplicationUserRole.*;

@AllArgsConstructor
@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUserDao {
    private  final PasswordEncoder passwordEncoder;

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }
    private List<ApplicationUser> getApplicationUsers(){
        List<ApplicationUser> applicationUsers = Lists.newArrayList(
                new ApplicationUser(
                        ADMIN.getGranedAutorities(),
                        passwordEncoder.encode("password"),
                        "samuel",
                        true,
                        true,
                        true,
                        true
                ),
                new ApplicationUser(
                        AGENT.getGranedAutorities(),
                        passwordEncoder.encode("password"),
                        "amdin",
                        true,
                        true,
                        true,
                        true
                ),
                new ApplicationUser(
                        CUSTOMER.getGranedAutorities(),
                        passwordEncoder.encode("password"),
                        "znti",
                        true,
                        true,
                        true,
                        true
                )
        );
        return applicationUsers;
    }
}
